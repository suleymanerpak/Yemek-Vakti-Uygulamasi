package com.example.yemekvaktii.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"
        //API'nin temel URL'sini tanımlar.Tüm API istekleri bu URL üzerinden yapılacaktır.

        //YemeklerDao arayüzü ile API iletişimini sağlayan Retrofit istemcisini oluşturur.
        fun getYemeklerDao(): YemeklerDao {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
            //RetrofitClient'tan istemciyi alır ve YemeklerDao arayüzünü oluşturur.

        }

        //SepetDao arayüzü ile API iletişimini sağlayan Retrofit istemcisini oluşturur.
        fun getSepetDao(): SepetDao {
            return RetrofitClient.getClient(BASE_URL).create(SepetDao::class.java)
            //RetrofitClient'tan istemciyi alır ve SepetDao arayüzünü oluşturur.
        }
    }
}