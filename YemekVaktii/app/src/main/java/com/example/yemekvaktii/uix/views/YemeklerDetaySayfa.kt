package com.example.yemekvaktii.uix.views

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yemekvaktii.data.entity.Sepet_Yemekler
import com.example.yemekvaktii.data.entity.Yemekler
import com.example.yemekvaktii.uix.viewmodel.YemeklerDetayViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YemeklerDetaySayfa(gelenYemek : Sepet_Yemekler, navController: NavController, yemeklerDetayViewModel: YemeklerDetayViewModel) {

    /*
    var adet by remember { mutableStateOf(1) }
    var yildiz by remember { mutableStateOf(3) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDialog by remember { mutableStateOf(false) } // AlertDialog gösterimi için durum


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Emin misiniz?") },
            text = { Text(text = "${gelenYemek.yemek_siparis_adet} adet ${gelenYemek.yemek_adi} sepete eklensin mi?") },
            confirmButton = {
                TextButton(onClick = {
                    yemeklerDetayViewModel.addFoodToCart(
                        yemek_adi = gelenYemek.yemek_adi,
                        yemek_resim_adi = gelenYemek.yemek_resim_adi,
                        yemek_fiyat = gelenYemek.yemek_fiyat,
                        yemek_siparis_adet = gelenYemek.yemek_siparis_adet,
                        kullanici_adi = gelenYemek.kullanici_adi

                    )
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "${gelenYemek.yemek_siparis_adet} adet ${gelenYemek.yemek_adi} sepete eklendi.",
                            actionLabel = "Tamam"
                        )
                    }
                    showDialog = false // Dialogu kapat
                }) {
                    Text("Evet", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Hayır")
                }
            }
        )
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = gelenYemek.yemek_adi)}) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    )
    {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                for (i in 1..5){
                    Icon(
                        imageVector = if (i <= yildiz) Icons.Default.Star else Icons.Outlined.Star,
                        contentDescription = "Yildiz $i",
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { yildiz = i },
                        tint = Color.Yellow
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))

            val activity = (LocalContext.current as Activity)
            Image(bitmap = ImageBitmap.imageResource(id =
            activity.resources.getIdentifier(gelenYemek.yemek_resim_adi,"drawable",activity.packageName)),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp))

            Spacer(modifier = Modifier.height(18.dp))

            Text(text = "${gelenYemek.yemek_fiyat}₺", fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFF6D00)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = gelenYemek.yemek_adi,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6D00)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    onClick = { if (adet > 1) adet-- },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("-", color = Color.White, fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "$adet", fontSize = 20.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { adet++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("+", color = Color.White, fontSize = 24.sp)
                }
            }

            Spacer(modifier = Modifier
                .height(24.dp)
                .width(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Anında Kapında", fontSize = 12.sp, color = Color.Gray,fontWeight = FontWeight.Bold,)
                Text(text = "Hızlı Teslimat", fontSize = 12.sp, color = Color.Gray,fontWeight = FontWeight.Bold,)
                Text(text = "15-20 dk.", fontSize = 12.sp, color = Color.Gray,fontWeight = FontWeight.Bold,)
            }

            Spacer(modifier = Modifier
                .height(24.dp)
                .width(24.dp))

            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically ) {
                Text(
                    text = "${gelenYemek.yemek_fiyat * gelenYemek.yemek_siparis_adet} ₺",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6D00)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {showDialog = true
                        yemeklerDetayViewModel.addFoodToCart(
                            yemek_adi = gelenYemek.yemek_adi,
                            yemek_resim_adi = gelenYemek.yemek_resim_adi,
                            yemek_fiyat = gelenYemek.yemek_fiyat,
                            yemek_siparis_adet = gelenYemek.yemek_siparis_adet,
                            kullanici_adi = gelenYemek.kullanici_adi
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(8.dp)
                        .shadow(8.dp, CutCornerShape(topStart = 16.dp)),
                    shape = CutCornerShape(topStart = 24.dp, bottomEnd = 16.dp),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Text("Sepete Ekle",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, // Yazıyı kalınlaştırdım
                        letterSpacing = 1.5.sp // Harfler arasında boşluk ekledim
                    )
                }
            }

        }

    }

 */

    var yildiz by remember { mutableStateOf(3) }
    var adet by remember { mutableStateOf(1) }
    var showDialog by remember { mutableStateOf(false) } // AlertDialog gösterimi için durum

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = gelenYemek.yemek_adi, color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFF6D00) // Arka plan rengini turuncu yaptık (Hex kodu)
                )
            )
        },
    )

    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= yildiz) Icons.Default.Star else Icons.Outlined.Star,
                        contentDescription = "Yildiz $i",
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { yildiz = i },
                        tint = Color.Yellow
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${gelenYemek.yemek_fiyat}₺", fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFF6D00)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = gelenYemek.yemek_adi,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6D00)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    onClick = { if (adet > 1) adet-- },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("-", color = Color.White, fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "$adet", fontSize = 20.sp)

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { adet++ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("+", color = Color.White, fontSize = 24.sp)
                }
            }

            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
            )

            val toplamFiyat = gelenYemek.yemek_fiyat * adet

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$toplamFiyat ₺",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6D00)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        //showDialog = true

                        yemeklerDetayViewModel.addFoodToCart(
                            yemek_adi = gelenYemek.yemek_adi,
                            yemek_resim_adi = gelenYemek.yemek_resim_adi,
                            yemek_fiyat = gelenYemek.yemek_fiyat,
                            yemek_siparis_adet = adet,
                            kullanici_adi = "suleymanerpak",
                        )
                        Log.e("Navigasyon","Detay Sayfasından Veri Eşleme")


                        val yemekMap = mapOf(
                            "sepet_yemek_id" to gelenYemek.sepet_yemek_id,
                            "yemek_adi" to gelenYemek.yemek_adi,
                            "yemek_fiyat" to gelenYemek.yemek_fiyat,
                            "yemek_siparis_adet" to gelenYemek.yemek_siparis_adet,
                            "kullanici_adi" to "suleymanerpak" // Kullanıcı adını sabit verdik
                        )

                        val yemekJson = Gson().toJson(yemekMap)

                        // Yemekler Sepetim Sayfasına git
                        navController.navigate("yemeklerSepetimSayfa/$yemekJson")
                        Log.e("Navigasyon","Detay Sayfasından Sepet Sayfasına Veri Aktarimi ve Geçişi")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6D00)
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(8.dp)
                        .shadow(8.dp, CutCornerShape(topStart = 16.dp)),
                    shape = CutCornerShape(topStart = 24.dp, bottomEnd = 16.dp),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Text(
                        "Sepete Ekle",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, // Yazıyı kalınlaştırdım
                        letterSpacing = 1.5.sp // Harfler arasında boşluk ekledim
                    )
                }
        }
        }
    }
}


