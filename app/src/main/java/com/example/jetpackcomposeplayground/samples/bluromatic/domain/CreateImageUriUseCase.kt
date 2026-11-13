package com.example.jetpackcomposeplayground.samples.bluromatic.domain

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Bu Use Case sınıfı, Android 10 (API level 29) ve üzeri cihazlarda MediaStore API kullanarak
 * resmin kaydedileceği URI adresini ve meta verilerini (DISPLAY_NAME, RELATIVE_PATH, MIME_TYPE) oluşturur.
 */
@RequiresApi(29)
class CreateImageUriUseCase {
    /**
     * Arka planda I/O thread'inde çalışarak MediaStore üzerinde yeni bir resim URI'si kaydı açar.
     *
     * @param resolver Cihazın içerik sağlayıcısına (ContentResolver) erişim nesnesi.
     * @param filename Kaydedilecek resim dosyasının adı.
     * @return Oluşturulan resmin Uri adresi veya işlem başarısızsa null.
     */
    suspend operator fun invoke(resolver: ContentResolver, filename: String): Uri? {
        return withContext(Dispatchers.IO) {
            // Görsel medyanın kaydedileceği birincil harici depolama Uri koleksiyonu belirlenir.
            val imageCollection =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

            // Resim dosyasının meta verileri ContentValues paketine eklenir.
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Blur-O-Matic")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                // Dosya yazımı henüz bitmediği için geçici olarak yazıma kapalı (IS_PENDING = 1) olarak işaretlenir.
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            // MediaStore'a yeni kayıt eklenir ve boş resim URI adresi alınır.
            val imageUri = resolver.insert(imageCollection, values)
            imageUri
        }
    }
}
