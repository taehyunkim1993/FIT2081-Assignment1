package com.example.assignment.ui.screen

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.ui.theme.Shapes
import com.example.assignment.viewmodel.UserViewModel

@Composable
fun InsightsScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val context = LocalContext.current
    val currentUser by userViewModel.currentUser.collectAsState()

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "From NutriTrack. TBC.")
        type = "text/plain"
    }

    Column(modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text("Insights: Food Score", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Fruits")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Fruits?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))

            Text(currentUser?.scores?.Fruits.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Grains & Cereals")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.GrainsAndCereals?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.GrainsAndCereals.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Whole Grains")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Wholegrains?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Wholegrains.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Meat & Alternatives")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.MeatAndAlternatives?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f),
                )
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.MeatAndAlternatives.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Dairy")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Dairy?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Dairy.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Water")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Water?.div(5f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Water.toString() + "/5")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Unsaturated Fats")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.UnsaturatedFats?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.UnsaturatedFats.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Sodium")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Sodium?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Sodium.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Sugar")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Sugar?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Sugar.toString() + "/10")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Alcohol")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Slider(value = currentUser?.scores?.Alcohol?.div(5f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Alcohol.toString() + "/5")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Discretionary Foods")
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))

            Slider(value = currentUser?.scores?.Discretionary?.div(10f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.Discretionary.toString() + "/10")
        }

        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Text("Total Food Quality Score",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Slider(value = currentUser?.scores?.HEIFAtotal?.div(100f) ?: 0f,
                onValueChange = {},
                modifier = Modifier.weight(1f))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
            Text(currentUser?.scores?.HEIFAtotal.toString() + "/100")
        }
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Button(onClick = {
            shareScore(context, currentUser?.scores?.HEIFAtotal.toString())
        },
            shape = Shapes.small,
            modifier = Modifier.height(dimensionResource(R.dimen.button_height_sm))) {
            Icon(painterResource(R.drawable.share), contentDescription = "Share",
                modifier = Modifier.size(dimensionResource(R.dimen.icon_medium)))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_medium)))
            Text("Share with someone")
        }
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Button(onClick = { /* TBC */},
            shape = Shapes.small,
            modifier = Modifier.height(dimensionResource(R.dimen.button_height_sm))) {
            Icon(painterResource(R.drawable.improve), contentDescription = "Improve",
                modifier = Modifier.size(dimensionResource(R.dimen.icon_medium)))
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_medium)))
            Text("Improve my diet!")
        }
        Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
    }
}

private fun shareScore(context: Context, score: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Total Food Quality Score $score")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
