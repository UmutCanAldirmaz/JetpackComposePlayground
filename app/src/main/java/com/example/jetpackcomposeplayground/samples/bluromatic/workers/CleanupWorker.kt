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
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.bluromatic.DELAY_TIME_MILLIS
import com.example.jetpackcomposeplayground.samples.bluromatic.OUTPUT_PATH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

private const val TAG = "CleanupWorker"

/**
 * Bu sınıf, Blur-O-Matic işlemlerinden kalan eski geçici resim dosyalarını
 * silerek cihazın depolama alanını temizleyen CoroutineWorker sınıfıdır.
 */
class CleanupWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    /**
     * Bu fonksiyon, arka planda dosya temizleme işlemini yürütür.
     */
    override suspend fun doWork(): Result {

        // Bu kod, temizlik işleminin başladığını kullanıcıya bildirim olarak bildirir.
        makeStatusNotification(
            applicationContext.resources.getString(R.string.cleaning_up_files),
            applicationContext
        )

        // Bu kod, dosya silme işlemlerini I/O iş parçacığında çalıştırır.
        return withContext(Dispatchers.IO) {
            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                // Bu kod, uygulama dizinindeki geçici resimlerin saklandığı klasörü bulur.
                val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)

                // Bu kod, eğer klasör varsa içerisindeki eski .png uzantılı geçici dosyaları tek tek siler.
                if (outputDirectory.exists()) {
                    val entries = outputDirectory.listFiles()
                    if (entries != null) {
                        for (entry in entries) {
                            val name = entry.name
                            if (name.isNotEmpty() && name.endsWith(".png")) {
                                val deleted = entry.delete()
                                Log.i(TAG, "Silinen dosya: $name - Başarılı mı: $deleted")
                            }
                        }
                    }
                }

                // Bu kod, temizlik işleminin başarıyla tamamlandığını bildirir.
                Result.success()
            } catch (exception: Exception) {
                // Bu kod, silme sırasında hata oluşursa hatayı loglar ve işin başarısız olduğunu bildirir.
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_cleaning_file),
                    exception
                )
                Result.failure()
            }
        }
    }
}
