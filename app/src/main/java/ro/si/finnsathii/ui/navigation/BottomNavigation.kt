package ro.si.finnsathii.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object Budget : BottomNavItem(
        route = Screen.Budget.route,
        title = "Budget",
        icon = Icons.Default.List
    )
    object Achievements : BottomNavItem(
        route = Screen.Achievements.route,
        title = "Achievements",
        icon = Icons.Default.Star
    )
    object Profile : BottomNavItem(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Budget,
        BottomNavItem.Achievements,
        BottomNavItem.Profile
    )
) {
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
