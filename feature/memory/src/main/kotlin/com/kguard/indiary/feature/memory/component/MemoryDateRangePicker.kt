package com.kguard.indiary.feature.memory.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryDateRangePicker(
    onConfirm: (String) -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext
    var openDialog by remember { mutableStateOf(false) }
    val dateRangePickerState = rememberDateRangePickerState()
    OutlinedButton(
        onClick = { openDialog = true },
        shape = RoundedCornerShape(20),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.onPrimaryContainer
        ),
        contentPadding = PaddingValues(start = 6.dp, end = 8.dp)
    )
    {
        Icon(
            imageVector = Icons.Rounded.CalendarMonth,
            contentDescription = "add",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(id = R.string.Date),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelLarge
        )
    }
    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    if (dateRangePickerState.selectedEndDateMillis != null) {
                        val endDate =
                            parseLongToLocalDateTime(dateRangePickerState.selectedEndDateMillis!!)
                        if (!LocalDate.now().plusDays(1).isAfter(endDate.toLocalDate())) {
                            Toast.makeText(contextForToast, "미래 날짜는 선택할 수 없습니다", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            openDialog = false
                            onConfirm(dateRangePickerState.selectedStartDateMillis?.let {
                                getFormattedDate(
                                    it
                                )
                            } + "~" + getFormattedDate(dateRangePickerState.selectedEndDateMillis!!))
                        }

                    }
                }) {
                    Text(
                        text = "확인",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                // Seems broken at the moment with DateRangePicker
                // Works fine with DatePicker
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text(
                        text = "취소",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        ) {

            DateRangePicker(
                modifier = Modifier.weight(1f), // Important to display the button
                state = dateRangePickerState,
                title = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "기간을 선택해주세요",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                headline = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = dateRangePickerState.selectedStartDateMillis?.let {
                                getFormattedDate(
                                    it
                                )
                            }
                                ?: "시작 날짜",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = "~",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text =
                            dateRangePickerState.selectedEndDateMillis?.let { getFormattedDate(it) }
                                ?: "종료 날짜",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            )
        }

    }
}

fun parseLongToLocalDateTime(long: Long): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(long),
        ZoneId.systemDefault()
    ) // systemDefault는 현재 디바이스의 지역을 의미함
}

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(timeInMillis: Long): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = timeInMillis
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    return dateFormat.format(calender.timeInMillis)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, device = "spec:width=392.7dp,height=850.9dp,dpi=440")
fun MemoryDateRangePickerPrev() {
    IndiaryTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MemoryDateRangePicker(onConfirm = {
                })
            }
    }
}