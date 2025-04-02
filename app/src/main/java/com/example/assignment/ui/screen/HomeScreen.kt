package com.example.assignment.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment.model.User
import com.example.assignment.ui.layout.MainNavigationBottomBar
import com.example.assignment.viewmodel.UserViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

@Composable
fun HomeScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val context = LocalContext.current

    val currentUser: User? by userViewModel.currentUser.collectAsState()

    Log.d("currentUser", currentUser?.toString() ?: "logged in user: null")

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text("Home Screen")
        Text(currentUser?.id ?: "no logged in user")
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