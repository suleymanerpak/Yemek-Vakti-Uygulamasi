package com.example.yemekvaktii.data.repo

import com.example.yemekvaktii.data.datasource.SepetDataSource

class SepetRepository (var sds : SepetDataSource) { //"sds" değişkeni, SepetDataSource türünde bir nesne alır.

    suspend fun addFoodToCart( //Sepete yemek eklemek için gerekli bilgileri alır.
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = sds.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    //"sds" nesnesinin "addFoodToCart" fonksiyonunu çağırır.

    suspend fun getCartFoods(kullanici_adi: String) = sds.getCartFoods(kullanici_adi)
    //fonksiyon sepet yemeklerini alır.
    //"sds" nesnesinin "getCartFoods" fonksiyonunu çağırır.

    suspend fun deleteFoodFromCart(sepet_yemek_id: Int, kullanici_adi: String) = sds.deleteFoodFromCart(sepet_yemek_id, kullanici_adi)
    //Fonksiyon, belirtilen yemek ID'sine göre sepetten yemek siler.
    //"sds" nesnesinin "deleteFoodFromCart" fonksiyonunu çağırır.

}