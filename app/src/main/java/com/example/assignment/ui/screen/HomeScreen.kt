package com.example.assignment.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment.ui.layout.MainNavigationBottomBar
import com.example.assignment.viewmodel.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.assignment.R
import com.example.assignment.navigation.Routes
import com.example.assignment.ui.theme.Shapes

@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val currentUser by userViewModel.currentUser.collectAsState()

    var showScore by remember { mutableStateOf(false) }

    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navController.navigate(Routes.LOGIN.route) {
                popUpTo(Routes.HOME.route) { inclusive = true }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.Center) {
        Text(text= "Hello,", style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline)
        Text(text= currentUser?.id ?: "Unknown", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))


        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text("You've already filled in your Food Intake Questionnaire, but "
            + "you can change details here: ", modifier = Modifier.weight(1f))

            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Button(onClick = { navController.navigate(Routes.FOODINQ.route)},
                shape = Shapes.small,
                modifier = Modifier.width(dimensionResource(R.dimen.button_width_ml))
                    .height(dimensionResource(R.dimen.button_height_small))) {
                Icon(Icons.Default.Edit, contentDescription = "Edit",
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_medium)))
                Spacer(Modifier.width(dimensionResource(R.dimen.padding_medium)))
                Text("Edit")
            }
        }

        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Image(painterResource(R.drawable.home_food), contentDescription = "food")}
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text("My Score", style= MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.clickable { showScore = !showScore }
                .padding(dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically) {
                if (!showScore) {
                    Text("See all scores", color = MaterialTheme.colorScheme.outline)
                } else {
                    Text("Hide all scores", color = MaterialTheme.colorScheme.outline)
                }
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Arrow")
            }
        }
        if (showScore) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(R.drawable.arrow_up), contentDescription = "Hide Score",
                        modifier = Modifier.clickable { showScore = false }
                            .background(Color.LightGray.copy(alpha = 0.6f), shape = CircleShape)
                            .size(dimensionResource(R.dimen.icon_medium)))
                    Spacer(Modifier.width(dimensionResource(R.dimen.padding_large)))
                    Text("Your Food Quality score")
                }
                Text(currentUser?.scores?.HEIFAtotal.toString() + "/100")
            }
        }

        /* TODO GET FOOD SCORE*/
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        HorizontalDivider()
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Text("What is the Food Quality Score?", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Text("Your Food Quality Score provides a snapshot of how well your eating patterns align "
        + "with established food guidelines, helping you identify both strengths and opportunities "
        + "for improvement in your diet.")

        Spacer(Modifier.height(dimensionResource(R.dimen.padding_large)))
        Text("This personalized measurement considers various food groups including vegetables, "
        + "fruits, whole grains, and proteins to give you practical insights for making healthier "
        + "food choices.")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        MainNavigationBottomBar(navController = navController)
    }) {
            innerPadding -> Column(modifier = Modifier.fillMaxSize().padding(innerPadding)){
        HomeScreen(navController, userViewModel = viewModel(
            factory = UserViewModel.Factory(context = LocalContext.current)))
    }
    }
}