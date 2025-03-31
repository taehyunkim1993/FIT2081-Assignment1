package com.example.assignment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment.navigation.MainNavHost
import com.example.assignment.navigation.Routes

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
        .value?.destination?.route

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute == Routes.HOME.route ||
                currentRoute == Routes.INSIGHTS.route){
                /* TODO MAIN BOTTOM BAR */
                Text("bottom bar")
            }
        }){
        innerPadding ->
        MainNavHost(navHostController = navController,
            modifier = Modifier.padding(innerPadding))
    }
}