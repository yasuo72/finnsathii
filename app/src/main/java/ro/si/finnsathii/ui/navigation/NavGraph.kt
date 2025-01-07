package ro.si.finnsathii.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ro.si.finnsathii.navigation.NavigationRoutes
import ro.si.finnsathii.ui.screens.achievements.AchievementsScreen
import ro.si.finnsathii.ui.screens.auth.LoginScreen
import ro.si.finnsathii.ui.screens.auth.SignUpScreen
import ro.si.finnsathii.ui.screens.budget.AddBudgetScreen
import ro.si.finnsathii.ui.screens.budget.BudgetScreen
import ro.si.finnsathii.ui.screens.home.HomeScreen
import ro.si.finnsathii.ui.screens.profile.ProfileScreen
import ro.si.finnsathii.ui.screens.transaction.AddTransactionScreen
import ro.si.finnsathii.ui.viewmodel.AuthViewModel

sealed class Screen(val route: String) {
    object Login : Screen(NavigationRoutes.LOGIN)
    object SignUp : Screen(NavigationRoutes.SIGNUP)
    object Home : Screen(NavigationRoutes.HOME)
    object AddTransaction : Screen("add_transaction")
    object Budget : Screen("budget")
    object AddBudget : Screen("add_budget")
    object Achievements : Screen("achievements")
    object Profile : Screen("profile")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    onNavigateToLogin: () -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        authGraph(
            navController = navController,
            authViewModel = authViewModel,
            onAuthSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        )

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToAddTransaction = { navController.navigate(Screen.AddTransaction.route) },
                onNavigateToBudgets = { navController.navigate(Screen.Budget.route) },
                onTransactionClick = { /* Handle transaction click */ }
            )
        }

        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screen.Budget.route) {
            BudgetScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToAddBudget = {
                    navController.navigate(Screen.AddBudget.route)
                }
            )
        }

        composable(Screen.AddBudget.route) {
            AddBudgetScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screen.Achievements.route) {
            AchievementsScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateToLogin = onNavigateToLogin
            )
        }
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    composable(Screen.Login.route) {
        LoginScreen(
            authViewModel = authViewModel,
            onNavigateToSignUp = {
                navController.navigate(Screen.SignUp.route)
            },
            onLoginSuccess = onAuthSuccess
        )
    }

    composable(Screen.SignUp.route) {
        SignUpScreen(
            authViewModel = authViewModel,
            onNavigateBack = {
                navController.popBackStack()
            },
            onSignUpSuccess = onAuthSuccess
        )
    }
}
