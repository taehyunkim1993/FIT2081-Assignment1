package com.example.assignment.ui.screen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment.R
import com.example.assignment.helper.saveStringToPref
import com.example.assignment.model.FoodCategory
import com.example.assignment.model.Persona
import com.example.assignment.model.saveFoodCategoriesSharedPref
import com.example.assignment.navigation.Routes
import com.example.assignment.ui.layout.FoodInQTopBar
import com.example.assignment.ui.modal.PersonaModalButton
import com.example.assignment.ui.theme.Shapes
import com.example.assignment.ui.utils.TimePickerDialogHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInQScreen(navController: NavHostController) {
    val context = LocalContext.current

    val foodCategories = FoodCategory.entries.toList()
    val checkedCategories = remember { mutableStateMapOf<FoodCategory, Boolean>().apply {
        foodCategories.forEach { put(it, false) }
    }}

    var personaDropdownExpanded by remember { mutableStateOf(false) }
    var selectedPersona by remember { mutableStateOf("")}

    val personas = Persona.entries.toList()

    var timeMeal = remember { mutableStateOf("")}
    var showMealTimePicker = remember { mutableStateOf(false) }

    var timeSleep = remember { mutableStateOf("")}
    var showSleepTimePicker = remember { mutableStateOf(false) }

    var timeWakeup = remember { mutableStateOf("")}
    var showWakeupTimePicker = remember { mutableStateOf(false) }


    LazyColumn(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_large))) {

        item {
                HorizontalDivider()
                Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = "Tick all the food categories you can eat",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        }

        item {
            foodCategories.chunked(3).forEach { rowFoodCategory ->
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                    verticalAlignment = Alignment.CenterVertically)
                {
                    rowFoodCategory.forEach { category ->
                        Box(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically) {

                                Checkbox(checked = checkedCategories[category] == true,
                                    onCheckedChange = { checkedCategories[category] = it })
                                Text(text=category.value,
                                    style = MaterialTheme.typography.labelMedium)
                            }

                        }
                    }
                }
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        }

        item {
            Text(
                text = "Your Persona",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
            Text(
                text = "People can be broadly classified into 6 different types based on " +
                        "their eating preferences. Click on each button below to find out the different " +
                        "types, and select the type that best fits you!",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        }

        item {
            personas.chunked(2).forEach { rowPersona ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowPersona.forEach { persona ->
                        Box(modifier = Modifier.weight(1f)) {
                            PersonaModalButton(persona)
                        }
                    }
                }
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.padding_large)))
        }


        item {
            HorizontalDivider()
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
            Text(
                text = "Which persona best fits you?", fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

            ExposedDropdownMenuBox(expanded = personaDropdownExpanded,
                onExpandedChange = { personaDropdownExpanded = it }) {
                OutlinedTextField(value = selectedPersona,
                    onValueChange = { selectedPersona = it },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().
                    menuAnchor(type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true),
                    shape = Shapes.large,
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown",
                            modifier = Modifier.clickable { personaDropdownExpanded = true })
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    placeholder = {Text(text = "Select option",
                        style = MaterialTheme.typography.labelLarge)})

                ExposedDropdownMenu(expanded = personaDropdownExpanded,
                    onDismissRequest = { personaDropdownExpanded = false }) {
                    personas.forEach { persona ->
                        DropdownMenuItem(text = {
                            Text(text = persona.value,
                            style = MaterialTheme.typography.labelLarge) },
                            onClick = {
                                selectedPersona = persona.value
                                personaDropdownExpanded = false
                            })
                    }
                }
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.button_height_medium)))
        }

        item {
            Text(
                text = "Timings", fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "What time of day approx. do you normally eat your biggest meal?",
                    modifier = Modifier.weight(1f))
                Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
                OutlinedTextField(value= timeMeal.value ,
                    onValueChange = { timeMeal.value = it },
                    readOnly = true,
                    placeholder = { Text("00:00") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null,
                            modifier = Modifier.clickable { showMealTimePicker.value = true })
                    },
                    modifier = Modifier.width(dimensionResource(R.dimen.button_width_large))
                        .height(dimensionResource(R.dimen.button_height_medium))
                        .padding(dimensionResource(R.dimen.padding_small))
                        .clickable { showMealTimePicker.value = true }
                )
            }
            if (showMealTimePicker.value) {
                TimePickerDialogHelper(timeMeal, showMealTimePicker)
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "What time of day approx. do you go to sleep at night?",
                    modifier = Modifier.weight(1f))
                Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
                OutlinedTextField(value=timeSleep.value,
                    onValueChange = { timeSleep.value = it },
                    readOnly = true,
                    placeholder = { Text("00:00") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null,
                            modifier = Modifier.clickable { showSleepTimePicker.value = true })
                    },
                    modifier = Modifier.width(dimensionResource(R.dimen.button_width_large))
                        .height(dimensionResource(R.dimen.button_height_medium))
                        .padding(dimensionResource(R.dimen.padding_small))
                        .clickable { showSleepTimePicker.value = true }
                )
            }
            if (showSleepTimePicker.value) {
                TimePickerDialogHelper(timeSleep, showSleepTimePicker)
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "What time of day approx. do you wake up in the morning?",
                    modifier = Modifier.weight(1f))
                Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))


                OutlinedTextField(value=timeWakeup.value,
                    onValueChange = { timeWakeup.value = it },
                    readOnly = true,
                    placeholder = { Text("00:00") },
                    modifier = Modifier.width(dimensionResource(R.dimen.button_width_large))
                        .height(dimensionResource(R.dimen.button_height_medium))
                        .clickable { showWakeupTimePicker.value = true }
                        .padding(dimensionResource(R.dimen.padding_small)),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null,
                            modifier = Modifier.clickable { showWakeupTimePicker.value = true })
                    }
                )

            }
            if (showWakeupTimePicker.value) {
                TimePickerDialogHelper(timeWakeup, showWakeupTimePicker)
            }
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
            HorizontalDivider()
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    val checkedCategoryList = checkedCategories.filter { it.value }.keys.toList()
                    saveFoodCategoriesSharedPref(context, checkedCategoryList)
                    saveStringToPref(context, "personaPref", "persona" ,selectedPersona)
                    saveStringToPref(context, "timePref", "timeMeal" ,timeMeal.value)
                    saveStringToPref(context, "timePref", "timeSleep" ,timeSleep.value)
                    saveStringToPref(context, "timePref", "timeWakeup" ,timeWakeup.value)
                    navController.navigate(Routes.HOME.route) },
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                        .width(dimensionResource(R.dimen.button_width_large))
                        .height(dimensionResource(R.dimen.button_height_small)),
                    shape = Shapes.small) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Save")
                    Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
                    Text("Save", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodInQ(){
    val navController = rememberNavController()
    Scaffold(topBar = {
        FoodInQTopBar(navController = navController)
    }) {
        innerPadding -> Column(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            FoodInQScreen(navController)
    }
    }
}