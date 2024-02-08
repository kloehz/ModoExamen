package com.example.modoexamen.shared.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.modoexamen.R
import kotlinx.coroutines.launch

data class TabInfo(val title: String, val icon: Int)

val tabs = listOf(
    TabInfo("Inicio", R.drawable.home_tab_bar),
    TabInfo("Promos", R.drawable.promo_tab_bar),
    TabInfo("Billetera", R.drawable.wallet_tab_bar),
    TabInfo("Más", R.drawable.menu_tab_bar)
)

@Composable
fun NavBar(navController: NavController) {
    var offsetState = remember { Animatable(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .padding(bottom = 20.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(color = colorResource(id = R.color.brandGray))
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp, top = 16.dp)){
            Box(
                modifier = Modifier
                    .offset(
                        offsetState.value.dp
                    )
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .width(70.dp)
                    .height(72.dp)
                    .background(color = colorResource(R.color.primaryWhite))
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, start = 20.dp, top = 16.dp)
                .onGloballyPositioned {cords ->
                    size = cords.size
                }
        ) {
            tabs.forEachIndexed { index, tabInfo ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                    .clickable {
                        // navController.navigate()
                        coroutineScope.launch {
                            offsetState.animateTo(
                                targetValue = 93f * index,
                                animationSpec = tween(
                                    durationMillis = 500,
                                    delayMillis = 0
                                )
                            )
                        }
                    }

                ) {
                    Image(
                        painter = painterResource(id = tabInfo.icon),
                        contentDescription = "home"
                    )
                    Text(text = tabInfo.title, fontSize = 10.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    val navController = rememberNavController()
    NavBar(navController)
}