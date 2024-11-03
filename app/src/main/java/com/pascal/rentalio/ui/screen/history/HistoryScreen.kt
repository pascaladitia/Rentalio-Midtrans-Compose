package com.pascal.rentalio.ui.screen.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pascal.rentalio.R
import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.base.UiState
import com.pascal.rentalio.ui.component.LoadingScreen
import com.pascal.rentalio.ui.component.ShowErrorDialog
import com.pascal.rentalio.ui.theme.RentalioTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.ArrowRight
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HistoryViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var showLoading by remember { mutableStateOf(false) }
    var showDialogError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val uiState by viewModel.historyState.collectAsState()
    var listData by remember { mutableStateOf<ResponseHistory?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    if (showLoading) {
        LoadingScreen()
    }

    ShowErrorDialog(
        title = "Rentalio",
        message = errorMessage,
        isDialogVisible = showDialogError
    ) {
        showDialogError = false
    }

    Surface(
        modifier = modifier.padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        HistoryContent(
            listData = listData
        )
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Empty -> {}
            is UiState.Loading -> {
                showLoading = true
            }

            is UiState.Error -> {
                val errorState = uiState as UiState.Error
                showLoading = false
                errorMessage = errorState.message
                showDialogError = true
            }

            is UiState.Success -> {
                showLoading = false
                listData = (uiState as UiState.Success).data
            }
        }
    }
}

@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    listData: ResponseHistory? = null
) {
    Column {
        Column(
            modifier = modifier.weight(1f)
        ) {
            HistoryHeader()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                items(listData?.records?.bookings ?: emptyList()) { item ->
                    HistoryItem(
                        item = item
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
fun HistoryHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Reservation List",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 14.sp,
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { }
                    .size(24.dp),
                imageVector = Icons.Default.AccessTime,
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

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    item: Booking? = null
) {
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = item?.vehicleImage)
                        .error(R.drawable.no_thumbnail)
                        .apply { crossfade(true) }
                        .build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = item?.codeBook ?: "-",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 14.sp
                    ),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item?.vehicleName ?: "-",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp),
                        imageVector = FeatherIcons.ArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = item?.dateStart ?: "-",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 12.sp
                        ),
                        color = Color.Gray
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp),
                        imageVector = FeatherIcons.ArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = item?.dateEnd ?: "-",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 12.sp
                        ),
                        color = Color.Gray
                    )
                }
            }
        }

        HorizontalDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {

                },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Reserving",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "SEE DETAILS",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 14.sp
                ),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HistoryPreview() {
    RentalioTheme {
        HistoryContent()
    }
}