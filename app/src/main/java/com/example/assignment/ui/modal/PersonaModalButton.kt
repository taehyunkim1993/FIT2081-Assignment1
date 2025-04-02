package com.example.assignment.ui.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.assignment.R
import com.example.assignment.model.Persona
import com.example.assignment.ui.theme.Shapes

@Composable
fun PersonaModalButton(persona: Persona) {
    var showDialog by remember { mutableStateOf(false) }

    Button(onClick = { showDialog = true },
        shape = Shapes.small
    ) {
        Text(text = persona.value,
            style = MaterialTheme.typography.labelLarge)
    }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false}) {
            Card(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(persona.imageResId), contentDescription = persona.value,
                        modifier = Modifier.size(dimensionResource(R.dimen.image_size_large)))
                    Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

                    Text(text = persona.value,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

                    Text(text = persona.description,
                        style = MaterialTheme.typography.bodyMedium)

                    Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
                    Button(onClick = { showDialog = false},
                        shape = Shapes.small) {
                        Text(text = "Dismiss")
                    }

                }

            }
        }
    }
}