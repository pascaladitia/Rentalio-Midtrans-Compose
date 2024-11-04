package com.pascal.rentalio.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pascal.rentalio.R
import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.ui.component.DateRangePickerBottomSheet
import com.pascal.rentalio.ui.screen.history.HistoryViewModel
import com.pascal.rentalio.ui.theme.Brown
import com.pascal.rentalio.ui.theme.RentalioTheme
import com.pascal.rentalio.utils.getAddressFromLocation
import com.pascal.rentalio.utils.getLocationWithCheckNetworkAndGPS
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HistoryViewModel = koinViewModel(),
    viewModelDetail: DetailViewModel = koinViewModel(),
    onNavBack: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        onNavBack()
    }

    LaunchedEffect(Unit) {
        viewModelDetail.initPayment(context)
    }

    Surface(
        modifier = modifier.padding(paddingValues),
        color = Color.White
    ) {
        DetailContent(
            onBook = {
                coroutineScope.launch {
                    viewModel.addHistory(it)
                    viewModelDetail.payment(context)
                }
            },
            onNavBack = {
                onNavBack()
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    onBook: (Booking) -> Unit,
    onNavBack: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isDriver by remember { mutableStateOf(true) }
    var isTransmission by remember { mutableStateOf(true) }
    var isChecked by remember { mutableStateOf(false) }
    var dialogDate by remember { mutableStateOf(false) }

    var startDate by remember { mutableStateOf("DATE FROM") }
    var endDate by remember { mutableStateOf("DATE TO") }
    var totalDate by remember { mutableStateOf("0") }

    val sliderList = remember {
        mutableListOf(
            "I AGREE TO ALL TERMS AND CONDITION APPLIED\n(Please swipe right to see the terms & condition)",
            "SAYA SETUJU DENGAN SEMUA SYARAT DAN KETENTUAN YANG BERLAKU"
        )
    }
    val pagerState = rememberPagerState { sliderList.size }

    if (dialogDate) {
        DateRangePickerBottomSheet(
            totalValue = {
                totalDate = it
            },
            onDismiss = {
                dialogDate = false
            },
            onConfirm = { start, end ->
                startDate = start
                endDate = end
            }
        )
    }

    Column {
        Box(
            modifier = modifier.weight(1f)
        ) {
            Column {
                DetailHeader(
                    onNavBack = {
                        onNavBack()
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .weight(1.5f)
                            .height(150.dp),
                        painter = painterResource(R.drawable.honbrio),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "HONDA\nBRIO",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 24.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "4 SEAT",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 16.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "2010 - 2025",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 12.sp
                            ),
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Row {
                            Spacer(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(10.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            )

                            Spacer(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(10.dp)
                                    .background(Color.Gray)
                            )

                            Spacer(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(10.dp)
                                    .background(MaterialTheme.colorScheme.onSurface)
                            )

                            Spacer(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(10.dp)
                                    .background(Brown)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "DRIVER SERVICE",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 10.sp
                        ),
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        Box(
                            modifier = Modifier
                                .clickable { isDriver = true }
                                .weight(1f)
                                .height(56.dp)
                                .background(
                                    if (isDriver) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.background
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "WITH\nDRIVER",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = if (isDriver) Color.White
                                else MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Box(
                            modifier = Modifier
                                .clickable { isDriver = false }
                                .weight(1f)
                                .height(56.dp)
                                .background(
                                    if (isDriver) MaterialTheme.colorScheme.background
                                    else MaterialTheme.colorScheme.primary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "WITHOUT\nDRIVER",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = if (isDriver) MaterialTheme.colorScheme.onSurface
                                else Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = !isDriver
                    ) {
                        Column {
                            Text(
                                text = "DRIVER SERVICE",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row {
                                Box(
                                    modifier = Modifier
                                        .clickable { isTransmission = true }
                                        .weight(1f)
                                        .height(56.dp)
                                        .background(
                                            if (isTransmission) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.background
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "AT",
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                fontSize = 24.sp
                                            ),
                                            color = if (isTransmission) Color.White
                                            else MaterialTheme.colorScheme.onSurface
                                        )

                                        Text(
                                            text = "Automatic Transmission",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontSize = 8.sp
                                            ),
                                            color = if (isTransmission) Color.White
                                            else MaterialTheme.colorScheme.onSurface
                                        )
                                    }

                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                Box(
                                    modifier = Modifier
                                        .clickable { isTransmission = false }
                                        .weight(1f)
                                        .height(56.dp)
                                        .background(
                                            if (isTransmission) MaterialTheme.colorScheme.background
                                            else MaterialTheme.colorScheme.primary
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "MT",
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                fontSize = 24.sp
                                            ),
                                            color = if (isTransmission) MaterialTheme.colorScheme.onSurface
                                            else Color.White
                                        )

                                        Text(
                                            text = "Manual Transmission",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontSize = 8.sp
                                            ),
                                            color = if (isTransmission) MaterialTheme.colorScheme.onSurface
                                            else Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Column(
                            modifier = Modifier
                                .clickable { dialogDate = true }
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "FROM",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.background)
                                    .padding(6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = startDate,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 10.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier
                                .clickable { dialogDate = true }
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "TO",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.background)
                                    .padding(6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = endDate,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 10.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.background)
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Lokasi Saya",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 10.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(56.dp),
                            painter = painterResource(R.drawable.ic_price),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "PRICE ESTIMATE",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 8.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "RP. 350.000 / 24 HOUR\nX7 DAYS",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 8.sp
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "TOTAL",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 8.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "RP. 2.100.000",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 12.sp
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.background)
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(
                        modifier = Modifier.scale(0.8f),
                        checked = isChecked,
                        onCheckedChange = { isChecked = !isChecked },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = MaterialTheme.colorScheme.tertiary,
                            disabledCheckedColor = MaterialTheme.colorScheme.tertiary
                        ),
                    )

                    HorizontalPager(
                        state = pagerState,
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
                            .clip(RoundedCornerShape(0)))
                        {
                            Text(
                                text = sliderList[page],
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(sliderList.size) {
                        val color =
                            if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
                        Box(modifier = modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(10.dp)
                            .background(color)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            })
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onBook(
                            Booking(
                                codeBook = "RNTL1",
                                vehicleName = "HONDA BRIO",
                                vehicleImage = "image",
                                dateStart = startDate,
                                dateEnd = endDate
                            )
                        )
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    enabled = isChecked
                ) {
                    Text(
                        text = "BOOK NOW",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
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
fun DetailHeader(
    modifier: Modifier = Modifier,
    onNavBack: () -> Unit
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
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onNavBack() }
                    .size(24.dp),
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )

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

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    RentalioTheme {
        DetailContent(
            onBook = {},
            onNavBack = {}
        )
    }
}