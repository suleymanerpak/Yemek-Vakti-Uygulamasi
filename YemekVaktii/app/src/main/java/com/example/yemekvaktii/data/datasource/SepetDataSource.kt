package com.example.yemekvaktii.data.datasource

import android.util.Log
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.retrofit.SepetDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SepetDataSource (var sdao:SepetDao ){//"sdao" değişkeni SepetDao türünde bir nesne alır.

    //Fonksiyon, kullanıcının sepetindeki yemekleri almak için kullanılır.
    suspend fun getCartFoods(kullanici_adi: String): List<Sepet_Yemekler> = withContext(Dispatchers.IO) {
        try {
            val response = sdao.getCartFoods(kullanici_adi) //"sdao" nesnesinin 'getCartFoods' fonksiyonunu çağırarak sepet verilerini alır.
            Log.e("API Yanıtı", response.toString()) // Gelen yanıtı loglar.

            val cartFoods = response.sepet_yemekler ?: emptyList() //Sepet yemeklerini alır, eğer yoksa boş bir liste döndürür.

            if (cartFoods.isEmpty()) { //Eğer sepet yemekleri boşsa,
                Log.e("Sepet Bilgisi", "Sepet boş veya yemekler bulunamadı.")
            }
            return@withContext cartFoods //Sepet yemekleri döner.

        } catch (e: Exception) { //Eğer bir hata oluşursa,
            Log.e("Hata", "API'den veri çekilirken hata oluştu: ${e.message}")
            return@withContext emptyList<Sepet_Yemekler>() //Boş bir liste döner.
        }
    }

    //Fonksiyon, sepetine yemek eklemek için kullanılır.
    suspend fun addFoodToCart(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        sdao.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        //"sdao" nesnesinin "addFoodToCart" fonksiyonunu çağırarak yemeği sepete ekler.
    }

    //Fonksiyon, sepetten yemek silmek için kullanılır.
    suspend fun deleteFoodFromCart(sepet_yemek_id: Int, kullanici_adi: String) {
        sdao.deleteFoodFromCart(sepet_yemek_id, kullanici_adi)
        //"sdao" nesnesinin "deleteFoodFromCart" fonksiyonunu çağırarak yemeği sepetten siler.
        Log.e("Yemek Silme Sonucu", sdao.deleteFoodFromCart(sepet_yemek_id, kullanici_adi).message)
    }

}