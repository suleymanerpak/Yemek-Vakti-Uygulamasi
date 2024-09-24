package com.example.yemekvaktii.uix.views

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.uix.viewmodel.YemeklerSepetimViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import com.example.yemekvaktii.R
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YemeklerSepetimSayfa(gelenYemek : Sepet_Yemekler, yemeklerSepetimViewModel: YemeklerSepetimViewModel) {

    val yemeklerListesi = yemeklerSepetimViewModel.sepetListesi.observeAsState(listOf())

    LaunchedEffect(key1 = true)
    {
        yemeklerSepetimViewModel.getCartFoods("suleymanerpak")
        yemeklerSepetimViewModel.totalFiyatiHesapla()

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Sepetim", color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFF6D00) // Arka plan rengini turuncu yaptık (Hex kodu)
                )
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(4.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            if (yemeklerListesi.value.isEmpty()) {
                Text(
                    text = "Sepetiniz Boş.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn( modifier = Modifier.weight(1f)){
                    items(yemeklerListesi.value){yemek->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .height(150.dp)
                                .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color.White,),
                            shape = RoundedCornerShape(4.dp),


                            )
                        {
                            Row( modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {

                                val resimUrl =
                                    "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
                                GlideImage(imageModel = resimUrl,
                                    modifier = androidx.compose.ui.Modifier.size(100.dp, 120.dp).
                                    clip(RoundedCornerShape(8.dp)),
                                    failure = {
                                        // Görsel yüklenemezse burası çalışır
                                        Text("Resim Yüklenemedi")
                                        Log.e("Resim Yüklenemedi", "Resim Yüklenemedi")
                                    }
                                )

                                Spacer(modifier = Modifier.width(20.dp))
                                Column(modifier = Modifier.weight(1f).width(140.dp)) {
                                    Text(text = yemek.yemek_adi,
                                        fontWeight = FontWeight.Bold, fontSize = 14.sp,
                                        color = Color.Black)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "${yemek.yemek_fiyat}₺",
                                        fontSize = 14.sp,
                                        color = Color.Black)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Adet: ${yemek.yemek_siparis_adet}", fontSize = 12.sp,color = Color.Gray)
                                }
                                Spacer(modifier = Modifier.width(100.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    IconButton(
                                        onClick = {
                                            yemeklerSepetimViewModel.deleteFoodFromCart(yemek.sepet_yemek_id,"suleymanerpak")
                                        }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Sil",
                                            tint = Color.Red
                                        )
                                    }
                                    Text(
                                        text = "${yemek.yemek_siparis_adet * yemek.yemek_fiyat} ₺",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Blue)
                                }
                            }
                        }
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
                {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Gönderim Ücreti", fontSize = 12.sp, color = Color.Gray)
                        Text(text = "0 ₺", fontSize = 12.sp , color = Color.Gray)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Toplam :", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = "${yemeklerSepetimViewModel.toplamFiyat.value} ₺", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Blue)
                    }

                }

                Button(
                    onClick = { /* Sepeti Onayla işlemi */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .shadow(8.dp, CutCornerShape(topStart = 16.dp)),
                    shape = CutCornerShape(topStart = 24.dp, bottomEnd = 16.dp),
                    border = BorderStroke(2.dp, Color.White),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                ) {
                    Text(text = "SEPETİ ONAYLA", fontWeight = FontWeight.Bold, fontSize = 16.sp,letterSpacing = 1.5.sp)
                }
            }
        }
    }


    /*
    val yemeklerListesi = remember { mutableStateListOf<Sepet_Yemekler>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Sepetim") })
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(4.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            if (yemeklerListesi.isEmpty()) {
                Text(
                    text = "Sepetiniz boş.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            else{
                LazyColumn( modifier = Modifier.weight(1f)){
                    items(yemeklerListesi){yemek->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .height(140.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White,),
                            shape = RoundedCornerShape(4.dp),
                            )
                        {
                            Row( modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {
                                val resimUrl =
                                    "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
                                GlideImage(imageModel = resimUrl,
                                    modifier = androidx.compose.ui.Modifier.size(200.dp, 250.dp),
                                    failure = {
                                        // Görsel yüklenemezse burası çalışır
                                        Text("Resim Yüklenemedi")
                                        Log.e("Resim Yüklenemedi", "Resim Yüklenemedi")
                                    }
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Column(modifier = Modifier.weight(1f))
                                {
                                    Text(text = yemek.yemek_adi,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp,
                                        color = Color.Black)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Fiyat : 30₺",
                                        fontSize = 18.sp,
                                        color = Color.Black)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Adet: ${yemek.yemek_siparis_adet}", fontSize = 14.sp,color = Color.Gray)
                                }
                                Spacer(modifier = Modifier.width(150.dp))
                                Column(modifier = Modifier.weight(1f))
                                {
                                    IconButton(
                                        onClick = {
                                            yemeklerSepetimViewModel.deleteFoodFromCart(yemek.sepet_yemek_id,yemek.kullanici_adi)
                                        }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Sil",
                                            tint = Color.Red
                                        )
                                    }
                                    Text(
                                        text = "30₺",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Blue)
                                }
                            }
                        }
                }
            }

        }
    }

     */




}