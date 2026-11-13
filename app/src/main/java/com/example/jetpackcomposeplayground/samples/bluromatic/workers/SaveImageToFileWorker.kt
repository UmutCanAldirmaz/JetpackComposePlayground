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
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.bluromatic.DELAY_TIME_MILLIS
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_IMAGE_URI
import com.example.jetpackcomposeplayground.samples.bluromatic.domain.CreateImageUriUseCase
import com.example.jetpackcomposeplayground.samples.bluromatic.domain.SaveImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "SaveImageToFileWorker"

/**
 * Bu sınıf, BlurWorker tarafından oluşturulan bulanıklaştırılmış geçici resmi alıp
 * cihazın MediaStore galeri dizinine kalıcı olarak kaydeden CoroutineWorker sınıfıdır.
 */
class SaveImageToFileWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    /**
     * Bu fonksiyon, arka planda kalıcı görsel kaydetme işlemini yürütür.
     */
    override suspend fun doWork(): Result {
        // Bu kod, resim kaydetme adımının başladığını bildirim çubuğunda gösterir.
        makeStatusNotification(
            applicationContext.resources.getString(R.string.saving_image),
            applicationContext
        )

        return withContext(Dispatchers.IO) {
            // Emülatörde işlem adımlarının net görülebilmesi için yapay gecikme eklenir.
            delay(DELAY_TIME_MILLIS)

            val resolver = applicationContext.contentResolver
            return@withContext try {
                // Bu kod, bir önceki adımdan (BlurWorker) zincir yoluyla gelen geçici resim URI adresini okur.
                val resourceUri = inputData.getString(KEY_IMAGE_URI)

                // Bu kod, URI adresindeki geçici resmi Bitmap olarak açar.
                val bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )

                val imageUrl: String?
                // Android 10 (API 29) ve üzeri cihazlarda Scoped Storage uyumlu UseCase'ler kullanılır.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val createImageUriUseCase = CreateImageUriUseCase()
                    val saveImageUseCase = SaveImageUseCase()

                    // MediaStore üzerinde benzersiz isimle yeni resim URI'si oluşturulur.
                    val imageUri = createImageUriUseCase(
                        resolver,
                        "${title}_${dateFormatter.format(Date())}.jpg"
                    )

                    // Resim verisi oluşturulan URI adresine kaydedilir.
                    saveImageUseCase(resolver, imageUri, bitmap)
                    imageUrl = imageUri.toString()
                } else {
                    // Android 9 ve öncesi cihazlarda eski MediaStore yöntemi kullanılır.
                    @Suppress("DEPRECATION")
                    imageUrl = MediaStore.Images.Media.insertImage(
                        resolver, bitmap, title, dateFormatter.format(Date())
                    )
                }

                // Bu kod, eğer resim başarıyla kaydedildiyse galeri adresini sonuç paketi (outputData) olarak döndürür.
                if (!imageUrl.isNullOrEmpty()) {
                    val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                    Result.success(output)
                } else {
                    Log.e(
                        TAG,
                        applicationContext.resources.getString(R.string.writing_to_mediaStore_failed)
                    )
                    Result.failure()
                }
            } catch (exception: Exception) {
                // Bu kod, kaydetme sırasında hata oluşursa hatayı loglar ve işin başarısız olduğunu bildirir.
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_saving_image),
                    exception
                )
                Result.failure()
            }
        }
    }
}
