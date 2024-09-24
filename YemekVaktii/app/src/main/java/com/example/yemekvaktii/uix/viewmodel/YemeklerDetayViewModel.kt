package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.repo.SepetRepository
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemeklerDetayViewModel @Inject constructor (var srepo : SepetRepository) : ViewModel(){

    //Sepete yemek eklemek için tanımlanan fonksiyon.
    fun addFoodToCart(yemek_adi: String, yemek_resim_adi :String,yemek_fiyat : Int,yemek_siparis_adet : Int,kullanici_adi:String)
    {
        CoroutineScope(Dispatchers.Main).launch {
            srepo.addFoodToCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            //Sepet Repository'sinden yemek ekleme işlemi yapılır
        }
        Log.e("Navigasyon","Sepete Yemek Ekleme")
    }
}