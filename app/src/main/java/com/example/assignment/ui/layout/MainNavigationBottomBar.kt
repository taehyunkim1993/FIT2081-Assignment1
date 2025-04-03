package com.example.assignment.ui.layout

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment.R
import com.example.assignment.model.BottomNavItem
import com.example.assignment.navigation.Routes

@Composable
fun MainNavigationBottomBar(navController: NavHostController) {
    var selectedNavItem by remember { mutableIntStateOf(0) }

    val navItems: List<BottomNavItem> = listOf(
        BottomNavItem(Routes.HOME.route, "Home", R.drawable.home),
        BottomNavItem(Routes.INSIGHTS.route, "Insights", R.drawable.insight),
        BottomNavItem(Routes.NUTRICOACH.route, "NutriCoach", R.drawable.coach),
        BottomNavItem(Routes.SETTINGS.route, "Settings", R.drawable.setting)
    )

    NavigationBar {
        navItems.forEachIndexed { index, navItem ->
            NavigationBarItem(onClick =  { selectedNavItem = index
                                         navController.navigate(navItem.route)},
                selected = selectedNavItem == index,
                icon = { Icon(painterResource(navItem.iconId), contentDescription = navItem.title,
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_medium)))},
                label = {
                    Text(navItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                ))
        }
    }
}