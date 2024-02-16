package com.example.modoexamen.shared.components.NavBar

import android.util.Log
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.modoexamen.R
import com.example.modoexamen.features.home.presentation.view.HomeFragment
import com.example.modoexamen.features.promos.presentation.view.PromotionsFragment
import kotlinx.coroutines.launch

data class TabInfo(val title: String, val icon: Int, val testId: String)

val tabs = listOf(
    TabInfo("Inicio", R.drawable.home_tab_bar, "START_TEST_TAG"),
    TabInfo("Promos", R.drawable.promo_tab_bar, "PROMOS_TEST_TAG"),
    TabInfo("Billetera", R.drawable.wallet_tab_bar, "WALLET_TEST_TAG"),
    TabInfo("Más", R.drawable.menu_tab_bar, "MORE_TEST_TAG")
)

@Composable
fun NavBarr(navController: NavController) {
    var indexSelected by remember { mutableIntStateOf(0) }
    var columnWidth by remember { mutableFloatStateOf(0f) }
    var boxBackgroundSize by remember { mutableIntStateOf(0) }

    // TEST
    val transition = updateTransition(targetState = indexSelected, label = "")
    val offset by transition.animateDp(
        transitionSpec = { tween(durationMillis = 500, delayMillis = 0) }, label = ""
    ) {
        it * (82.dp) + (it * 6).dp
    }
    // TEST


    Box(
        modifier = Modifier
            .padding(bottom = 20.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(color = colorResource(id = R.color.brandGray))
            //.blur(radius = 2.dp)
            .testTag("NAVBAR_TEST_TAG")
    ) {
        TabRow(
            selectedTabIndex = indexSelected,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(
                        x = offset
                    )
                    .width(columnWidth.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .width(70.dp)
                        .height(72.dp)
                        .onGloballyPositioned { layoutCoordinates ->
                            boxBackgroundSize = layoutCoordinates.size.width
                        }
                        .background(color = colorResource(R.color.primaryWhite))
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    columnWidth = layoutCoordinates.size.width.toFloat() / 4
                }
                .padding(end = 36.dp, start = 32.dp, top = 8.dp)
        ) {
            tabs.forEachIndexed { index, tabInfo ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 16.dp)
                    .clickable {
                        indexSelected = index
                    }
                    .onGloballyPositioned {
//                        Log.d("Position: ",
//                            it.positionInParent().toString())
//                        Log.d("Position: ",
//                            it.positionInRoot().toString())
//                        Log.d("Position: ",
//                            it.positionInWindow().toString())
                    }
                    .testTag(tabInfo.testId)) {
                    Icon(
                        painter = painterResource(id = tabInfo.icon),
                        contentDescription = "tab_icon",
                        tint = colorResource(id = if (indexSelected == index) R.color.primaryPaymentDark else R.color.primaryBlack)
                    )
                    Text(text = tabInfo.title, fontSize = 10.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavBar(navController: NavController) {
    val pagerState = rememberPagerState { tabs.size }
    var indexSelected by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.primaryBlack)),
            state = pagerState,
        ) { page ->
                Log.d("savedInstanceState: ", page.toString())
            when (page) {
                0 -> HomeFragment()//navController.navigate(R.id.homeFragment)
                1 -> navController.navigate(R.id.promotionsFragment)
                2 -> navController.navigate(R.id.promotionsFragment)
                3 -> navController.navigate(R.id.promotionsFragment)
                else -> HomeFragment()//navController.navigate(R.id.homeFragment)
            }
        }

        TabRow(
            selectedTabIndex = indexSelected,
            modifier = Modifier
                .zIndex(6f)
                .padding(16.dp)
        ) {
            tabs.forEachIndexed { index, tabInfo ->
                Tab(
                    modifier = Modifier.zIndex(7f),
                    text = { Text(text = tabInfo.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                            indexSelected = index
                        }
                    },
                )
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