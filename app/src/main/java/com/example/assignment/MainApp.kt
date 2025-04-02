package com.example.assignment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment.navigation.MainNavHost
import com.example.assignment.navigation.Routes
import com.example.assignment.ui.layout.FoodInQTopBar
import com.example.assignment.ui.layout.MainNavigationBottomBar

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
        .value?.destination?.route

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            if (currentRoute == Routes.FOODINQ.route) {
                FoodInQTopBar(navController)
            }
        },
        bottomBar = {
            if (currentRoute == Routes.HOME.route ||
                currentRoute == Routes.INSIGHTS.route){
                MainNavigationBottomBar(navController = navController)
            }
        }){
        innerPadding ->
        MainNavHost(navHostController = navController,
            modifier = Modifier.padding(innerPadding))
    }
}