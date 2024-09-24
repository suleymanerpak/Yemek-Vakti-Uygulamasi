package com.example.yemekvaktii.di

import com.example.yemekvaktii.data.datasource.SepetDataSource
import com.example.yemekvaktii.data.datasource.YemeklerDataSource
import com.example.yemekvaktii.data.repo.SepetRepository
import com.example.yemekvaktii.data.repo.YemeklerRepository
import com.example.yemekvaktii.retrofit.ApiUtils
import com.example.yemekvaktii.retrofit.SepetDao
import com.example.yemekvaktii.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule { //Uygulama genelindeki bağımlılıkları sağlayan bir sınıf tanımlar.

    @Provides
    @Singleton
    fun provideYemeklerRepository(yds : YemeklerDataSource) : YemeklerRepository { //YemeklerRepository nesnesini sağlayan metod.
        return YemeklerRepository(yds) //YemeklerDataSource'u kullanarak YemeklerRepository oluşturur.
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao : YemeklerDao) : YemeklerDataSource{ //YemeklerDataSource nesnesini sağlayan metod.
        return YemeklerDataSource(ydao) //YemeklerDao'yu kullanarak YemeklerDataSource oluşturur.
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{ //YemeklerDao nesnesini sağlayan metod.
        return ApiUtils.getYemeklerDao() //ApiUtils sınıfından YemeklerDao nesnesini alır.
    }

    @Provides
    @Singleton
    fun provideSepetRepository(sds : SepetDataSource) : SepetRepository { //SepetRepository nesnesini sağlayan metod.
        return SepetRepository(sds) //SepetDataSource'u kullanarak SepetRepository oluşturur.
    }


    @Provides
    @Singleton
    fun provideSepetDataSource(sdao : SepetDao) : SepetDataSource{ //SepetDataSource nesnesini sağlayan metod.
        return SepetDataSource(sdao) //SepetDao'yu kullanarak SepetDataSource oluşturur.
    }

    @Provides
    @Singleton
    fun provideSepetDao() : SepetDao{ //SepetDao nesnesini sağlayan metod.
        return ApiUtils.getSepetDao() //ApiUtils sınıfından SepetDao nesnesini alır.
    }

}