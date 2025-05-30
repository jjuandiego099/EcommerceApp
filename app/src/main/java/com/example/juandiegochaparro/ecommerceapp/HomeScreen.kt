package com.example.juandiegochaparro.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.juandiegochaparro.ecommerceapp.ui.theme.EcommerceAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navcontroller: NavController) {
    val auth = Firebase.auth
    var user = auth.currentUser
    var correo = "No existe usuario"
    if (user != null) {
        correo = user.email.toString()
    } else {
        correo = "No existe usuario"

    }
    Scaffold(
        topBar = {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            MediumTopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "$correo",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        auth.signOut()
                        navcontroller.navigate("LoginScreen") {
                            popUpTo(0)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Promociones",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
            )
            val listaUrls =
                listOf(
                    "https://image.isu.pub/130216004219-d1d0bbd465de473d874c225605b30a2c/jpg/page_1_thumb_large.jpg",
                    "https://img.freepik.com/vector-premium/promocion-banner-super-venta_131000-345.jpg",
                    "https://img.freepik.com/vector-gratis/plantilla-banner-mega-venta-ofertas_1017-31299.jpg",
                    "https://thumbs.dreamstime.com/z/dise%C3%B1o-de-plantillas-banner-s%C3%BAper-venta-para-promociones-medios-y-promoci%C3%B3n-sociales-fondo-183029584.jpg",
                    "https://www.regaloscorporativos.com/wp-content/uploads/2016/08/Banner-Promociones-y-Saldos-2.jpg"
                )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {

                for (url in listaUrls) {
                    item { CardPromo(url) }
                }

            }

        }
    }
}


@Composable
fun CardPromo(url: String) {

    Card(
        modifier = Modifier
            .height(180.dp)
            .width(300.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Promocion",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }


}



