package com.pascal.rentalio.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pascal.rentalio.utils.AppConstants.Companion.FORMAT_DATE
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerBottomSheet(
    totalValue: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val coroutineScope = rememberCoroutineScope()
    val dateRangePickerState = rememberDateRangePickerState()
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var startDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }
    var endDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }
    var total by remember {
        mutableLongStateOf(0)
    }

    if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
        startDate = dateRangePickerState.selectedStartDateMillis ?: startDate
        endDate = dateRangePickerState.selectedEndDateMillis ?: endDate

        val totalMillis = endDate - startDate
        val totalDays = TimeUnit.MILLISECONDS.toDays(totalMillis)
        total = totalDays
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(0.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable { onDismiss() }
                            .size(24.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { },
                        text = "Clear",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 10.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }

                DateRangePicker(
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .weight(1f),
                    state = dateRangePickerState,
                    title = {},
                    showModeToggle = false,
                )

                HorizontalDivider()

                Row {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box {
                            if (total != 0L) {
                                Text(
                                    text = "$total Days Selected",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 14.sp
                                    ),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Button(
                            onClick = {
                                if (dateRangePickerState.selectedStartDateMillis != null && dateRangePickerState.selectedEndDateMillis != null) {
                                    startDate = dateRangePickerState.selectedStartDateMillis ?: startDate
                                    endDate = dateRangePickerState.selectedEndDateMillis ?: endDate

                                    val formatter = SimpleDateFormat(FORMAT_DATE, Locale.ROOT)
                                    val totalMillis = endDate - startDate
                                    val totalDays = TimeUnit.MILLISECONDS.toDays(totalMillis)
                                    total = totalDays
                                    totalValue("$totalDays")

                                    onConfirm(
                                        formatter.format(Date(startDate)),
                                        formatter.format(Date(endDate))
                                    )
                                    coroutineScope.launch {
                                        modalBottomSheetState.hide()
                                        onDismiss()
                                    }
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            enabled = dateRangePickerState.selectedStartDateMillis != null
                                    && dateRangePickerState.selectedEndDateMillis != null
                        ) {
                            Text(
                                text = "Save",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 12.sp
                                ),
                                color = MaterialTheme.colorScheme.background,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    )
}
