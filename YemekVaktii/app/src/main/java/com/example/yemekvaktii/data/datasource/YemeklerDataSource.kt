package com.example.yemekvaktii.data.datasource

import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydao : YemeklerDao) {//"ydao" değişkeni, YemeklerDao türünde bir nesne alır.

    //"getFoods" adında bir suspend fonksiyonu tanımlanır.Bu fonksiyon, yemekleri almak için kullanılır.
    suspend fun getFoods() : List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext ydao.getFoods().yemekler
        //"ydao" nesnesinin "getFoods" fonksiyonunu çağırarak yemekleri alır ve return eder.
    }


}