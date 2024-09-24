package com.example.yemekvaktii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yemekvaktii.ui.theme.YemekVaktiiTheme
import com.example.yemekvaktii.uix.viewmodel.AnasayfaViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerDetayViewModel
import com.example.yemekvaktii.uix.viewmodel.YemeklerSepetimViewModel
import com.example.yemekvaktii.uix.views.Anasayfa
import com.example.yemekvaktii.uix.views.SayfaGecisleri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anasayfaViewModel: AnasayfaViewModel by viewModels()
    val yemeklerDetayViewModel: YemeklerDetayViewModel by viewModels()
    val yemeklerSepetimViewModel: YemeklerSepetimViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YemekVaktiiTheme {
                SayfaGecisleri(anasayfaViewModel,yemeklerDetayViewModel,yemeklerSepetimViewModel)
            }
        }
    }
}
