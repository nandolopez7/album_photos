package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceGallery()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceGallery() {
    var currentArtwork by remember { mutableStateOf(1) }
    val imageResource = getImageResource(currentArtwork)
    val titleResource = getTitleResource(currentArtwork)
    val yearResource = getYearResource(currentArtwork)
    val descriptionResource = getDescriptionResource(currentArtwork)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier.size(15.dp)
        )
        Text(
            text = stringResource(R.string.name),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.azul_titulos),
            fontSize = 20.sp,
        )

        Text(
            text = stringResource(R.string.codigo),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.gray_300),
            fontSize = 16.sp,
        )

        ArtworkImage(
            currentArtwork = imageResource
        )


        ArtworkDetails(
            title = titleResource,
            year = yearResource,
            description = descriptionResource,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(
            modifier = Modifier.size(15.dp)
        )
        ArtworkNavigation(
            currentArtwork = currentArtwork,
            onPreviousClick = { currentArtwork = getPreviousArtwork(currentArtwork) },
            onNextClick = { currentArtwork = getNextArtwork(currentArtwork) },
            onRestartClick = { currentArtwork = 1 }
        )
    }
}

@Composable
fun ArtworkImage(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(id = currentArtwork),
        contentDescription = null,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(340.dp)
            .shadow(
                elevation = 10.dp,
                shape = MaterialTheme.shapes.medium,
                spotColor = colorResource(id = R.color.azul_borde)
            ),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkDetails(
    @StringRes title: Int,
    @StringRes year: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.azul_titulos),
            fontSize = 32.sp,
        )
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        Text(
            text = stringResource(id = year),
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300),
            fontSize = 18.sp,
        )
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        Text(
            text = stringResource(id = description),
            modifier = Modifier.width(350.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_900),
            fontSize = 15.sp,
        )
    }
}

@Composable
fun ArtworkNavigation(
    currentArtwork: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onRestartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.azul_botones),
                containerColor = colorResource(id = R.color.azul_botones)
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp,
            )
        ) {
            Text(
                text = stringResource(R.string.name_boton_previous),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.white)
            )
        }
        Button(
            onClick = onRestartClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.azul_botones),
                containerColor = colorResource(id = R.color.azul_botones)
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp
            ),
        ) {
            Image(
                painter = painterResource(R.drawable.rotate),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.azul_botones),
                containerColor = colorResource(id = R.color.azul_botones)
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp
            ),
        ) {
            Text(
                text = stringResource(R.string.name_boton_next),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@DrawableRes
private fun getImageResource(index: Int): Int {

    return when (index) {
        1 -> R.drawable.pic_1
        2 -> R.drawable.pic_2
        3 -> R.drawable.pic_3
        4 -> R.drawable.pic_4
        5 -> R.drawable.pic_5
        6 -> R.drawable.pic_6
        7 -> R.drawable.pic_7
        8 -> R.drawable.pic_8
        9 -> R.drawable.pic_9
        10 -> R.drawable.pic_10
        else -> R.drawable.pic_10
    }
}

@StringRes
private fun getTitleResource(index: Int): Int {

    return when (index) {
        1 -> R.string.foto0
        2 -> R.string.foto1
        3 -> R.string.foto2
        4 -> R.string.foto3
        5 -> R.string.foto4
        6 -> R.string.foto5
        7 -> R.string.foto6
        8 -> R.string.foto7
        9 -> R.string.foto8
        10 -> R.string.foto9
        else -> R.string.foto9
    }
}

@StringRes
private fun getYearResource(index: Int): Int {

    return when (index) {
        1 -> R.string.foto0_año
        2 -> R.string.foto1_año
        3 -> R.string.foto2_año
        4 -> R.string.foto3_año
        5 -> R.string.foto4_año
        6 -> R.string.foto5_año
        7 -> R.string.foto6_año
        8 -> R.string.foto7_año
        9 -> R.string.foto8_año
        10 -> R.string.foto9_año
        else -> R.string.foto9_año
    }
}

@StringRes
private fun getDescriptionResource(index: Int): Int {

    return when (index) {
        1 -> R.string.foto0_des
        2 -> R.string.foto1_des
        3 -> R.string.foto2_des
        4 -> R.string.foto3_des
        5 -> R.string.foto4_des
        6 -> R.string.foto5_des
        7 -> R.string.foto6_des
        8 -> R.string.foto7_des
        9 -> R.string.foto8_des
        10 -> R.string.foto9_des
        else -> R.string.foto9_des
    }
}

private fun getPreviousArtwork(currentIndex: Int): Int {
    return if (currentIndex == 1) 10 else currentIndex - 1
}

private fun getNextArtwork(currentIndex: Int): Int {

    return if (currentIndex == 10) 1 else currentIndex + 1
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceGallery()
    }
}
