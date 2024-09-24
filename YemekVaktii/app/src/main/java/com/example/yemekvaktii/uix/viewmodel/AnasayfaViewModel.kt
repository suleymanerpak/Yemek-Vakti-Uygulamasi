package com.example.yemekvaktii.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor (var yrepo : YemeklerRepository) : ViewModel(){

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init { //ViewModel ilk oluşturulduğunda çalışacak kod bloğu.
        getFoods()
        Log.e("Yemekleri Getirme","Yemekleri Getirme")
    }

    fun getFoods() //Yemekleri almak için tanımlanan fonksiyon.
    {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value =  yrepo.getFoods()
            //Yemekler listesini repository'den alır ve LiveData'ya atar.
        }
        Log.e("Yemekleri Getirme","Yemekleri Getirme")
    }

}