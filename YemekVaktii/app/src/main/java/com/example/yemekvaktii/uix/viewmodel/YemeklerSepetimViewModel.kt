package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.repo.SepetRepository
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemeklerSepetimViewModel @Inject constructor (var srepo : SepetRepository) : ViewModel() {

    var sepetListesi = MutableLiveData<List<Sepet_Yemekler>>() //Sepet içerisindeki yemekleri tutmak için kullanılan LiveData.
    var toplamFiyat = MutableLiveData<Int>() // Sepetteki toplam fiyatı tutmak için kullanılan LiveData.

    init {
        getCartFoods("suleymanerpak") // Başlangıçta sepetteki yemekleri getirir.
        Log.e("Navigasyon","Sepetteki Yemekleri Getirme - Sepet Sayfa")
    }

    //Sepetteki yemekleri getiren fonksiyon.
    fun getCartFoods(kullanici_adi: String)
    {
        CoroutineScope(Dispatchers.Main).launch {
            sepetListesi.value = srepo.getCartFoods(kullanici_adi) //SepetRepository üzerinden sepet yemeklerini alır.
            totalFiyatiHesapla()
        }
    }

    //Sepetten yemek silen fonksiyon.
    fun deleteFoodFromCart(sepet_yemek_id : Int, kullanici_adi: String)
    {
        CoroutineScope(Dispatchers.Main).launch {
            srepo.deleteFoodFromCart(sepet_yemek_id, kullanici_adi)
            getCartFoods("suleymanerpak") //Sepetteki yemekleri tekrar getirir.
            totalFiyatiHesapla() //Silme işleminden sonra toplam fiyatı hesaplar.
        }
        Log.e("Sepetteki Yemekleri Silme","Sepetteki Yemekleri Silme")
    }

    //Toplam fiyatı hesaplayan fonksiyon.
    fun totalFiyatiHesapla(){
        val toplam = sepetListesi.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        toplamFiyat.value = toplam ?: 0
    }


     





}