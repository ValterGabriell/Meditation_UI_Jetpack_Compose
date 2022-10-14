package com.example.meditation_ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation_ui.PathUtil.standardQuadTo
import com.example.meditation_ui.ui.theme.*


@Composable
fun HomeScren() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSession()
            ChipSession(chips = listOf("Bom sono!", "Insonia", "Depressão"))
            CurrentMeditation()
            FeatureSession(
                listFeatures = listOf(
                    Feature(
                        title = "Meditação Noturna",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Dicas para dormir",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Ilha da noite",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Sons calmos",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
    }

    BottomMenu(
        items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditar", R.drawable.ic_bubble),
            BottomMenuContent("Dormir", R.drawable.ic_moon),
            BottomMenuContent("Musica", R.drawable.ic_music),
            BottomMenuContent("Perfil", R.drawable.ic_profile)
        )
    )

}

@Composable
fun GreetingSession(name: String = "Gabriel") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bom dia $name",
                style = MaterialTheme.typography.h2
            )

            Text(
                text = "Desejamos que tenha um bom dia!",
                style = MaterialTheme.typography.h2
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Buscar",
            tint = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSession(chips: List<String>) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectItemItens: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectItemItens)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighLightColor = activeHighLightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }

        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent, isSelected: Boolean = false,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(if (isSelected) activeHighLightColor else Color.Transparent)
        ) {
            Icon(
                painter = painterResource(item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(25.dp)
            )
        }

        Text(text = item.title, color = if (isSelected) activeTextColor else inactiveTextColor)


    }

}

@Composable
fun CurrentMeditation(color: Color = LightRed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Pensamento do dia",
                style = MaterialTheme.typography.h2
            )

            Text(
                text = "Meditação | 3-10 min",
                style = MaterialTheme.typography.h2,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Iniciar",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSession(listFeatures: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Funcionalidades",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(listFeatures.size) {
                FeatureItem(feature = listFeatures[it])
            }
        }

    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadTo(mediumColoredPoint4, mediumColoredPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        val lightColoredPoint1 = Offset(0f, height * 0.35f)
        val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3)

        val lightColoredPath = Path().apply {
            moveTo(lightColoredPoint1.x, lightColoredPoint2.y)
            standardQuadTo(lightColoredPoint1, lightColoredPoint2)
            standardQuadTo(lightColoredPoint2, lightColoredPoint3)
            standardQuadTo(lightColoredPoint3, lightColoredPoint4)
            standardQuadTo(lightColoredPoint4, lightColoredPoint5)

            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            Text(
                text = "Iniciar",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        //click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}