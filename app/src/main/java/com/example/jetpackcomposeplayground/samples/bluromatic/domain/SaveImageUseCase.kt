package com.example.jetpackcomposeplayground.samples.bluromatic.domain

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "SaveImage"

/**
 * Bu Use Case sınıfı, oluşturulan Bitmap görsel verisini ContentResolver üzerinden
 * MediaStore klasörüne akış (OutputStream) olarak yazar ve yazım bitince dosyayı kullanıma açar.
 */
class SaveImageUseCase {
    /**
     * Arka planda I/O thread'inde çalışarak bitmap verisini belirtilen URI adresine kaydeder.
     *
     * @param resolver İçerik çözücü (ContentResolver) nesnesi.
     * @param contentUri Resmin yazılacağı hedef URI.
     * @param bitmap İşlenmiş ve kaydedilecek Bitmap nesnesi.
     */
    suspend operator fun invoke(
        resolver: ContentResolver,
        contentUri: Uri?,
        bitmap: Bitmap,
    ) = withContext(Dispatchers.IO) {
        try {
            contentUri?.let { uri ->
                // Belirtilen URI için yazılabilir çıkış akışı (OutputStream) açılır.
                resolver.openOutputStream(uri, "w")?.use { outputStream ->
                    // Bitmap verisi %85 kalite ile JPEG formatında akışa sıkıştırılarak yazılır.
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
                }

                // Resim yazma işlemi tamamlandığı için IS_PENDING bayrağı 0'a çekilerek dosya galeriye açılır.
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.IS_PENDING, 0)
                }
                resolver.update(uri, values, null, null)
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Görsel kaydetme sırasında hata oluştu: $e")
            throw e
        }
    }
}
