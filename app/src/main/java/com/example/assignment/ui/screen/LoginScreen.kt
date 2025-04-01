package com.example.assignment.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assignment.viewmodel.UserListViewModel
import androidx.compose.runtime.getValue // Important for the 'by' keyword
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.assignment.R
import com.example.assignment.ui.theme.Shapes
import androidx. compose. material3.MenuAnchorType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.assignment.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController,
                viewModel: UserListViewModel = viewModel(
                    factory = UserListViewModel.Factory(context = LocalContext.current))) {
    val userList by viewModel.userList.collectAsState()
    val userListLoading by viewModel.isLoading.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    var selectedUserId by rememberSaveable { mutableStateOf("") }
    var inputPhoneNumber by rememberSaveable { mutableStateOf("") }

    val validationResult by viewModel.validationResult.collectAsState()

    LaunchedEffect(key1 = userListLoading) {
        if (!userListLoading && userList.isNotEmpty()) {
            selectedUserId = userList[0].id
        }
    }


    Column(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_large)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,)
    {

        Text(text = "Log in",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        ExposedDropdownMenuBox(expanded = expanded,
            onExpandedChange = { expanded = it }) {
            OutlinedTextField(value = selectedUserId,
                readOnly = true,
                label = { Text(text = "My ID (Provided by your Clinician)",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge) },

                onValueChange = { selectedUserId = it },
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
                shape = Shapes.medium,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = true })
                }
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
                userList.forEach { user ->
                    DropdownMenuItem(
                        text = { Text(user.id) },
                        onClick = {
                            selectedUserId = user.id
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        OutlinedTextField(value = inputPhoneNumber,
            label = { Text(text = "Phone Number",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge) },
            onValueChange = { inputPhoneNumber = it },
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.medium,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done),
            )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        Text(text = "This app is only for pre-registered users. Please have your ID " +
        "and phone number handy before continuing.")

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        Button(onClick = { viewModel.validateUser(id = selectedUserId,
            phoneNumber = inputPhoneNumber)},
            modifier = Modifier.fillMaxWidth()
                .height(dimensionResource(R.dimen.image_size_medium)),
            shape = Shapes.medium
            ) {
            Text(text = "Continue",
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        if (validationResult != null) {
            if (validationResult == true) {
                navController.navigate(Routes.FOODINQ.route)
            } else {
                Text(text = "Please check your details.",
                    color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
