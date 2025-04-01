package com.example.assignment.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.navigation.Routes
import com.example.assignment.ui.theme.Shapes

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()
        .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.weight(1f))
        Text("NutriTrack",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.image_size_large))
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        Text("This app provides general health and nutrition information for " +
                "educational purposes only. It is not intended as medical advice, " +
                "diagnosis, or treatment. Always consult a qualified healthcare " +
                "professional before making any changes to your diet, exercise, or " +
                "health regimen.\n" +
                "Use this app at your own risk.\n" +
                "If you’d like to an Accredited Practicing Dietitian (APD), please" +
                "visit the Monash Nutrition/Dietetics Clinic (discounted rates for" +
                "students):\n" +
                "https://www.monash.edu/medicine/scs/nutrition/clinics/nutrition",
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
//        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { navController.navigate(Routes.LOGIN.route )},
            shape = Shapes.medium,
            modifier = Modifier.fillMaxWidth()
                .height(dimensionResource(R.dimen.image_size_medium)),
        ) {
            Text(text = "Login",
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Designed by Taehyun Kim (25082604)",
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)))
    }
}
