/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpackcomposeplayground.samples.bluromatic

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.BlurWorker
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.CleanupWorker
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.SaveImageToFileWorker
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Bu test sınıfı, Bluromatic uygulamasının WorkManager parçacıklarını (CleanupWorker, BlurWorker, SaveImageToFileWorker)
 * cihaz ortamında (Android Instrumentation Test) doğrudan sınamak için oluşturulmuştur.
 */
class WorkManagerWorkerInstrumentationTest {

    // Test sırasında WorkManager sınıflarına verilecek Android Context nesnesi.
    private lateinit var context: Context

    // Test işlemleri için mock girdi URI'si (Uygulamanın drawable kaynağındaki android_cupcake resmi).
    private val mockUriInput: Pair<String, String> =
        KEY_IMAGE_URI to "android.resource://com.example.jetpackcomposeplayground/drawable/android_cupcake"

    /**
     * Her test çalıştırılmadan önce çalışır ve uygulamanın Android Context nesnesini başlatır.
     */
    @Before
    fun setUp() {
        // Test ortamından uygulamanın gerçek Application Context nesnesi alınır.
        context = ApplicationProvider.getApplicationContext()
    }

    /**
     * CleanupWorker'ın geçici dosyaları silme işini başarıyla tamamlayıp tamamlamadığını doğrular.
     */
    @Test
    fun cleanupWorker_doWork_resultSuccess() {
        // TestListenableWorkerBuilder kullanılarak CleanupWorker nesnesi oluşturulur.
        val worker = TestListenableWorkerBuilder<CleanupWorker>(context).build()

        // doWork() suspend fonksiyonu coroutine ortamında çalıştırılır.
        runBlocking {
            val result = worker.doWork()

            // Sonucun Success (Başarılı) olduğu doğrulanır.
            assertTrue(result is ListenableWorker.Result.Success)
        }
    }

    /**
     * BlurWorker'ın girdi resmine bulanıklaştırıcı filtrenin uygulanıp uygulanmadığını ve
     * yeni geçici dosya URI adresini başarım çıktısı (outputData) olarak döndürdüğünü doğrular.
     */
    @Test
    fun blurWorker_doWork_resultSuccessReturnsUri() {
        // TestListenableWorkerBuilder ile BlurWorker oluşturulur ve mock resim URI'si girdi verisi olarak verilir.
        val worker = TestListenableWorkerBuilder<BlurWorker>(context)
            .setInputData(workDataOf(mockUriInput))
            .build()

        runBlocking {
            val result = worker.doWork()

            // Çıktı paketindeki (outputData) resim URI adresi okunur.
            val resultUri = result.outputData.getString(KEY_IMAGE_URI)

            // İşlemin başarılı olduğu doğrulanır.
            assertTrue(result is ListenableWorker.Result.Success)

            // Çıktı veri haritasının KEY_IMAGE_URI anahtarını barındırdığı doğrulanır.
            assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))

            // Oluşan URI adresinin dahili blur_filter_outputs dizin yoluyla başladığı doğrulanır.
            assertTrue(
                resultUri?.startsWith("file:///data/user/0/com.example.jetpackcomposeplayground/files/blur_filter_outputs/blur-filter-output-")
                    ?: false
            )
        }
    }

    /**
     * SaveImageToFileWorker'ın işlenmiş görseli cihaz galerisine (MediaStore API) kaydettiğini
     * ve geçerli bir MediaStore galeri URI adresi döndürdüğünü doğrular.
     */
    @Test
    fun saveImageToFileWorker_doWork_resultSuccessReturnsUrl() {
        // TestListenableWorkerBuilder ile SaveImageToFileWorker oluşturulur ve girdi verisi aktarılır.
        val worker = TestListenableWorkerBuilder<SaveImageToFileWorker>(context)
            .setInputData(workDataOf(mockUriInput))
            .build()

        runBlocking {
            val result = worker.doWork()

            // Çıktı paketindeki kaydedilen görselin galeri URI adresi okunur.
            val resultUri = result.outputData.getString(KEY_IMAGE_URI)

            // İşlemin başarılı olduğu doğrulanır.
            assertTrue(result is ListenableWorker.Result.Success)

            // Çıktı verisinin KEY_IMAGE_URI anahtarını barındırdığı doğrulanır.
            assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))

            // Android sürümüne göre MediaStore URI yolunun doğruluğu kontrol edilir:
            // Android 10+ (API 29+) için "content://media/external_primary/images/media/"
            // Android 9 ve öncesi için "content://media/external/images/media/"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                TestCase.assertTrue(
                    resultUri?.startsWith("content://media/external_primary/images/media/")
                        ?: false
                )
            } else {
                TestCase.assertTrue(
                    resultUri?.startsWith("content://media/external/images/media/")
                        ?: false
                )
            }
        }
    }
}
