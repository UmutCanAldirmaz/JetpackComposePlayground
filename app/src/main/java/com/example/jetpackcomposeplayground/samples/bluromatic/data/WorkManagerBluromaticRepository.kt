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

package com.example.jetpackcomposeplayground.samples.bluromatic.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.asFlow
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.jetpackcomposeplayground.samples.bluromatic.IMAGE_MANIPULATION_WORK_NAME
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_BLUR_LEVEL
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_IMAGE_URI
import com.example.jetpackcomposeplayground.samples.bluromatic.TAG_OUTPUT
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.BlurWorker
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.CleanupWorker
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.SaveImageToFileWorker
import com.example.jetpackcomposeplayground.samples.bluromatic.workers.getImageUri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

/**
 * Bu sınıf, WorkManager kütüphanesini kullanarak Blur-O-Matic işlemlerinin
 * arka planda zamanlanmasını, zincirlenmesini, kısıtlamalarla yönetilmesini ve takip edilmesini sağlayan Repository uygulamasıdır.
 */
class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {

    // Bulanıklaştırılacak varsayılan resmin Uri adresini Context üzerinden alır.
    private var imageUri: Uri = context.getImageUri()

    // Android sistemindeki WorkManager bileşeninin tekil (Singleton) örneğini alır.
    private val workManager = WorkManager.getInstance(context)

    // Tamamlanan veya devam eden zincirdeki çıktı işinin (TAG_OUTPUT) durumunu UI tarafına ileten Flow yapısıdır.
    override val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT).asFlow().mapNotNull {
            it.firstOrNull()
        }

    /**
     * Kullanıcı "Start" butonuna bastığında çağrılır.
     * Arka plan iş parçacıklarını sırasıyla çalıştırmak üzere bir İş Zinciri (WorkContinuation) oluşturur.
     *
     * @param blurLevel Uygulanacak bulanıklık seviyesi (1, 2 veya 3).
     */
    override fun applyBlur(blurLevel: Int) {
        // İş zincirini başlatır. Benzersiz isim (IMAGE_MANIPULATION_WORK_NAME) ve REPLACE politikası ile eski iş durdurulup yenisi başlar.
        // İlk sırada eski geçici dosyaları silen CleanupWorker çalışır.
        var continuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        // İşlemin cihaz pili düşükken çalışmasını engelleyen çalışma kısıtlaması (Constraints) oluşturulur.
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        // Resmi bulanıklaştırma işini (BlurWorker) yapacak WorkRequest yapılandırıcısı oluşturulur.
        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()

        // BlurWorker'a girdi verisi olarak resim URI'si ve blur seviyesi aktarılır.
        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))

        // Pil kısıtlaması blur iş isteğine eklenir.
        blurBuilder.setConstraints(constraints)

        // CleanupWorker bittikten hemen sonra BlurWorker'ın çalışması için zincire 2. halka eklenir.
        continuation = continuation.then(blurBuilder.build())

        // Bulanıklaştırılan resmi cihaza kaydedecek SaveImageToFileWorker isteği oluşturulur ve TAG_OUTPUT etiketi eklenir.
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .addTag(TAG_OUTPUT)
            .build()

        // BlurWorker bittikten hemen sonra SaveImageToFileWorker'ın çalışması için zincire 3. halka eklenir.
        continuation = continuation.then(save)

        // Oluşturulan sıralı iş zinciri (Cleanup -> Blur -> Save) WorkManager kuyruğuna eklenip başlatılır.
        continuation.enqueue()
    }

    /**
     * Devam eden veya kuyruktaki WorkManager benzersiz arka plan işlerini iptal eder.
     */
    override fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    /**
     * BlurWorker'a parametre olarak aktarılacak Data paketini (Key-Value) oluşturur.
     *
     * @param blurLevel Bulanıklaştırma seviyesi.
     * @param imageUri İşlenecek resmin URI adresi.
     * @return WorkManager için hazırlanmış Data nesnesi.
     */
    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}
