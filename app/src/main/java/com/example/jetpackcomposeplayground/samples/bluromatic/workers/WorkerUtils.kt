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

@file:Suppress("MissingPermission")

package com.example.jetpackcomposeplayground.samples.bluromatic.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.bluromatic.CHANNEL_ID
import com.example.jetpackcomposeplayground.samples.bluromatic.NOTIFICATION_ID
import com.example.jetpackcomposeplayground.samples.bluromatic.NOTIFICATION_TITLE
import com.example.jetpackcomposeplayground.samples.bluromatic.OUTPUT_PATH
import com.example.jetpackcomposeplayground.samples.bluromatic.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.jetpackcomposeplayground.samples.bluromatic.VERBOSE_NOTIFICATION_CHANNEL_NAME
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

private const val TAG = "WorkerUtils"

/**
 * Bu fonksiyon, arka plan iş parçacıkları başladığında kullanıcıya bildirim çubuğunda
 * durum bildirimleri (Heads-up Notification) göstermek için kullanılır.
 */
fun makeStatusNotification(message: String, context: Context) {

    // Bu kod, Android 8.0 (API 26) ve üzeri cihazlarda bildirimin gösterilebilmesi için bir NotificationChannel oluşturur.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    // Bu kod, gösterilecek bildirimin başlığını, simgesini ve içeriğini yapılandırır.
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Bu kod, hazırlanan bildirimi sistem bildirim yöneticisi ile kullanıcıya sunar.
    try {
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    } catch (e: SecurityException) {
        Log.e(TAG, "Notification izni bulunamadı", e)
    }
}

/**
 * Bu fonksiyon, kendisine verilen Bitmap resmi belirtilen blur seviyesinde piksel küçültüp büyüterek bulanıklaştırır.
 */
@WorkerThread
fun blurBitmap(bitmap: Bitmap, blurLevel: Int): Bitmap {
    val input = Bitmap.createScaledBitmap(
        bitmap,
        bitmap.width / (blurLevel * 5),
        bitmap.height / (blurLevel * 5),
        true
    )
    return Bitmap.createScaledBitmap(input, bitmap.width, bitmap.height, true)
}

/**
 * Bu fonksiyon, bulanıklaştırılan Bitmap nesnesini geçici bir PNG dosyası olarak diskte saklar ve dosyanın Uri adresini döndürür.
 */
@Throws(FileNotFoundException::class)
fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
    val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())
    val outputDir = File(applicationContext.filesDir, OUTPUT_PATH)
    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }
    val outputFile = File(outputDir, name)
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(outputFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, out)
    } finally {
        out?.let {
            try {
                it.close()
            } catch (e: IOException) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
    return Uri.fromFile(outputFile)
}

/**
 * Bu fonksiyon, varsayılan test resmi olan android_cupcake görselinin URI adresini oluşturan Context uzantısıdır.
 */
fun Context.getImageUri(): Uri {
    val resources = this.resources

    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
        .build()
}
