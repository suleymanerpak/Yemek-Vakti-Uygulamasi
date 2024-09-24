package com.example.yemekvaktii.retrofit

import com.example.yemekvaktii.data.entity.CRUDCevap
import com.example.yemekvaktii.data.entity.Sepet_YemeklerCevap
import com.example.yemekvaktii.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao { //Sepet işlemleri için API isteklerini tanımlayan arayüz.

    // http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods(): YemeklerCevap

}