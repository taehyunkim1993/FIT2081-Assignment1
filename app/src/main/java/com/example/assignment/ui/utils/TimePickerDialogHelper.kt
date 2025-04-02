package com.example.assignment.ui.utils

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun TimePickerDialogHelper(selectedTime: MutableState<String>, showTimePicker: MutableState<Boolean>) {
    val calendar = Calendar.getInstance()
    val timePickerDialog =  TimePickerDialog(LocalContext.current,
        {_, hourOfDay, minute ->
            selectedTime.value = "$hourOfDay:$minute"
        },
        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)

    timePickerDialog.setOnDismissListener {
        showTimePicker.value = false
    }

    timePickerDialog.show()
}