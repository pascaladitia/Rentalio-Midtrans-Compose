package com.pascal.rentalio.ui.screen.home

import android.Manifest
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.pascal.rentalio.R
import com.pascal.rentalio.ui.theme.RentalioTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = koinViewModel(),
    onDetail: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.historyState.collectAsState()

    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    )

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    Surface(
        modifier = modifier.padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        HomeContent(
            onDetail = {
                onDetail()
            }
        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDetail: () -> Unit
) {
    val sliderList = remember {
        mutableListOf(
            R.drawable.banner_slider,
            R.drawable.banner_slider_2
        )
    }

    Column {
        Column(
            modifier = modifier.weight(1f)
        ) {
            HomeHeader()

            CustomSlider(sliderList = sliderList)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .width(24.dp),
                        painter = painterResource(R.drawable.pocket),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "RENTALIO\nPOCKET",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 8.sp
                        ),
                        color = contentColorFor(MaterialTheme.colorScheme.onSurface)
                    )
                }

                Text(
                    modifier = Modifier.weight(1f),
                    text = "RP. 100.000,-",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp,
                    ),
                    color = contentColorFor(MaterialTheme.colorScheme.onSurface),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HomeItem(
                        icon = R.drawable.city,
                        text = "CITY",
                        isDisable = false
                    ) {
                        onDetail()
                    }

                    HomeItem(
                        icon = R.drawable.suv,
                        text = "SUV"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.mpv,
                        text = "MPV"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.jeep,
                        text = "JEEP"
                    ) {

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HomeItem(
                        icon = R.drawable.city,
                        text = "BUS"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.suv,
                        text = "BOX"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.mpv,
                        text = "VAN"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.jeep,
                        text = "SPORT"
                    ) {

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HomeItem(
                        icon = R.drawable.city,
                        text = "SPEC"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.trail,
                        text = "CUSTOM RIDE",
                        isDisable = false
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.mpv,
                        text = "YATCH"
                    ) {

                    }

                    HomeItem(
                        icon = R.drawable.jeep,
                        text = "JET"
                    ) {

                    }
                }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.city,
    text: String = "CITY",
    isDisable: Boolean = true,
    onDetail: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )

    Column(
        modifier = modifier
            .width(86.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                        onDetail()
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(48.dp * 1.2f)
                .height(48.dp * 1.2f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .scale(scale),
                painter = painterResource(icon),
                contentDescription = null,
                tint = if (isDisable) Color.Gray else Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 14.sp,
            ),
            color = if (isDisable) Color.Gray else MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(100.dp),
                painter = painterResource(R.drawable.logo2),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { }
                    .size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    sliderList: MutableList<Int>,
    backwardIcon: ImageVector = Icons.Default.ChevronLeft,
    forwardIcon: ImageVector = Icons.Default.ChevronRight,
    dotsActiveColor: Color = Color.DarkGray,
    dotsInActiveColor: Color = Color.LightGray,
    dotsSize: Dp = 10.dp,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 0.dp),
    imageCornerRadius: Dp = 0.dp,
    imageHeight: Dp = 200.dp,
) {

    val pagerState = rememberPagerState { sliderList.size }
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = pagerPaddingValues,
                modifier = modifier.fillMaxWidth()
            ) { page ->

                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

                val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)


                Box(modifier = modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .clip(RoundedCornerShape(imageCornerRadius))) {

                    Image(
                        painter = painterResource(sliderList[page]),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(imageHeight)
                            .alpha(if (pagerState.currentPage == page) 1f else 0.5f)
                    )

                }
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                enabled = pagerState.canScrollBackward,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                Icon(imageVector = backwardIcon, contentDescription = "back")
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                enabled = pagerState.currentPage != sliderList.size - 1,
                onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Icon(imageVector = forwardIcon, contentDescription = "forward")
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(sliderList.size) {
                    val color =
                        if (pagerState.currentPage == it) dotsActiveColor else dotsInActiveColor
                    Box(modifier = modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .size(dotsSize)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        })
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    RentalioTheme {
        HomeContent(
            onDetail = {}
        )
    }
}