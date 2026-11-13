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

package com.example.jetpackcomposeplayground.samples.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.bluromatic.DELAY_TIME_MILLIS
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_BLUR_LEVEL
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_IMAGE_URI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"

/**
 * Bu sınıf, WorkManager tarafından arka planda resmi bulanıklaştırma (blur) işlemini
 * yürütmek için çağrılan CoroutineWorker sınıfıdır.
 */
class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    /**
     * Bu fonksiyon, arka planda gerçekleştirilecek somut iş mantığını barındırır.
     */
    override suspend fun doWork(): Result {

        // Bu kod, Repository tarafından gönderilen girdi verisinden (inputData) resmin URI adresini okur.
        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        // Bu kod, Repository tarafından gönderilen girdi verisinden uygulanacak blur seviyesini okur (Varsayılan: 1).
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        // Bu kod, kullanıcının işin başladığını anlaması için bildirim (notification) çubuğunda mesaj gösterir.
        makeStatusNotification(
            applicationContext.resources.getString(R.string.blurring_image),
            applicationContext
        )

        // Bu kod, ağır görsel işleme adımlarını ana UI thread'i tıkamamak için I/O thread'ine taşır.
        return withContext(Dispatchers.IO) {
            // Bu kod, emülatörde veya hızlı cihazlarda arka plan iş adımlarının net görülebilmesi için yapay bir gecikme ekler.
            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                // Bu kod, resim URI adresinin boş olmadığını doğrular. Boşsa IllegalArgumentException fırlatır.
                require(!resourceUri.isNullOrBlank()) {
                    val errorMessage =
                        applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG, errorMessage)
                    errorMessage
                }

                // Bu kod, URI adresinden dosya okuyabilmek için ContentResolver nesnesini alır.
                val resolver = applicationContext.contentResolver

                // Bu kod, resim URI'sini akış (InputStream) olarak açar ve Bitmap nesnesine dönüştürür.
                val picture =
                    BitmapFactory.decodeStream(resolver.openInputStream(resourceUri.toUri()))

                // Bu kod, verilen blur seviyesine göre resmi bulanıklaştırarak yeni bir Bitmap üretir.
                val output = blurBitmap(picture, blurLevel)

                // Bu kod, bulanıklaştırılan Bitmap resmini geçici bir dosyaya yazar ve dosya URI adresini döndürür.
                val outputUri = writeBitmapToFile(applicationContext, output)

                // Bu kod, üretilen yeni geçici dosya URI'sini bir sonraki Worker'a iletmek üzere Data paketine dönüştürür.
                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

                // Bu kod, işin başarıyla bittiğini ve oluşan çıktı verisini (outputData) WorkManager'a bildirir.
                Result.success(outputData)
            } catch (throwable: Throwable) {
                // Bu kod, görsel işleme sırasında hata oluşursa hatayı loglar ve işin başarısız olduğunu bildirir.
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }
    }
}
