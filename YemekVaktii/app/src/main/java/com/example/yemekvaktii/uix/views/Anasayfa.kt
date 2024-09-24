package com.example.yemekvaktii.uix.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.yemekvaktii.uix.viewmodel.AnasayfaViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController, anasayfaViewModel: AnasayfaViewModel){

    val yemeklerListesi = anasayfaViewModel.yemeklerListesi.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        anasayfaViewModel.getFoods()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yemek Vakti") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "Home",tint = Color.White)
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = { Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Anasayfa",
                        tint = Color.Black)
                    },
                    label = {Text(text = "Anasayfa",color = Color.White)}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val yemekJson = Gson().toJson(anasayfaViewModel.getFoods())
                        navController.navigate("yemeklerSepetimSayfa/$yemekJson")
                    },
                    icon = { Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Sepetim", tint = Color.White)
                    },
                    label = {Text(text = "Sepetim",color = Color.White)}
                )
            }
        },


    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(12.dp)
                .padding(paddingValues)
                .fillMaxWidth()
        ) {items(yemeklerListesi.value.count()){index ->

            val yemek = yemeklerListesi.value[index]

            Card(
                modifier =Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
                    .clickable {
                        val yemekJson = Gson().toJson(yemek)
                        Log.d("Navigasyon", "Gönderilen Yemek JSON: $yemekJson")  // JSON verisini logla
                        navController.navigate("yemeklerDetaySayfa/$yemekJson")
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent  // Arka planı transparan yapıyoruz
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

                    GlideImage(imageModel =resimUrl,
                        modifier = androidx.compose.ui.Modifier.size(140.dp,160.dp),
                        failure = {
                            // Görsel yüklenemezse burası çalışır
                            Text("Resim Yüklenemedi")
                            Log.e("Resim Yüklenemedi","Resim Yüklenemedi")
                        }
                    )

                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = yemek.yemek_adi,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "${yemek.yemek_fiyat} ₺ ",
                        color = Color.Blue,
                        fontSize = 20.sp,
                        )
                }
            }
        }
        }
    }
}