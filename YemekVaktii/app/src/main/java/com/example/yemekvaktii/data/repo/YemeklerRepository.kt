package com.example.yemekvaktii.data.repo

import com.example.yemekvaktii.data.datasource.YemeklerDataSource

class YemeklerRepository(var yds: YemeklerDataSource) {//"yds" değişkeni, YemeklerDataSource türünde bir nesne alır.

    suspend fun getFoods()  = yds.getFoods()
    //Fonksiyon, yemek verilerini almak için "yds" nesnesinin "getFoods" fonksiyonunu çağırır.


}