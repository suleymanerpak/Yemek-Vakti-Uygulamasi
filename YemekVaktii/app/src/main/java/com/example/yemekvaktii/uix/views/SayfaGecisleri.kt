package com.example.yemekvaktii.uix.views

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.data.entity.YemeklerCevap
import com.example.yemekvaktii.uix.viewmodel.AnasayfaViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerDetayViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerSepetimViewModel
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,
                   yemeklerDetayViewModel: YemeklerDetayViewModel,
                   yemeklerSepetimViewModel: YemeklerSepetimViewModel
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }

        composable("yemeklerDetaySayfa/{yemekJson}",
            arguments = listOf(
                navArgument("yemekJson") {type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("yemekJson")
            Log.d("Navigasyon", "Detay Sayfasına Geçiş: $json")  // Geçişteki JSON verisini logla
            val nesne = Gson().fromJson(json, Sepet_Yemekler::class.java)
            if (nesne != null) {
                // Yemek bilgilerini ekrana yazdır
                YemeklerDetaySayfa(nesne, navController, yemeklerDetayViewModel)
            } else {
                // Hata mesajı göster
                Log.e("Navigasyon", "Yemek nesnesi çözümlenemedi!")
            }
        }

        composable("yemeklerSepetimSayfa/{yemek}",
            arguments = listOf(
                navArgument("yemek") {type = NavType.StringType}
            )
        ){
            val json2 = it.arguments?.getString("yemek")
            val nesne2 = Gson().fromJson(json2,Sepet_Yemekler::class.java)

            if(nesne2!= null)
            {
                YemeklerSepetimSayfa(nesne2,yemeklerSepetimViewModel)
            } else{
                // Hata mesajı göster
                Log.e("Navigasyon", "Yemek nesnesi çözümlenemedi!")
            }
        }
    }
}