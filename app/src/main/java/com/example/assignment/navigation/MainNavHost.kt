package com.example.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment.ui.screen.FoodInQScreen
import com.example.assignment.ui.screen.HomeScreen
import com.example.assignment.ui.screen.InsightsScreen
import com.example.assignment.ui.screen.LoginScreen
import com.example.assignment.ui.screen.WelcomeScreen

@Composable
fun MainNavHost(navHostController: NavHostController, modifier: Modifier) {
    NavHost(navController = navHostController,
        startDestination = Routes.WELCOME.route) {
        composable(Routes.WELCOME.route) {
            WelcomeScreen()
        }
        composable(Routes.LOGIN.route) {
            LoginScreen()
        }
        composable(Routes.FOODINQ.route){
            FoodInQScreen()
        }
        composable(Routes.HOME.route){
            HomeScreen()
        }
        composable(Routes.INSIGHTS.route){
            InsightsScreen()
        }
    }
}