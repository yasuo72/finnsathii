package ro.si.finnsathii.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ro.si.finnsathii.ui.screens.auth.LoginScreen
import ro.si.finnsathii.ui.screens.auth.SignUpScreen
import ro.si.finnsathii.ui.viewmodel.AuthViewModel

sealed class AuthRoute(val route: String) {
    object Login : AuthRoute("login")
    object SignUp : AuthRoute("signup")
    object Home : AuthRoute("home")
}

@Composable
fun AuthNavigation(
    navController: NavHostController = rememberNavController(),
    onAuthSuccess: () -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController, 
        startDestination = AuthRoute.Login.route
    ) {
        authNavigation(
            navController = navController,
            authViewModel = authViewModel,
            onAuthSuccess = onAuthSuccess
        )
    }
}

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    composable(AuthRoute.Login.route) {
        LoginScreen(
            authViewModel = authViewModel,
            onNavigateToSignUp = {
                navController.navigate(AuthRoute.SignUp.route)
            },
            onLoginSuccess = onAuthSuccess
        )
    }

    composable(AuthRoute.SignUp.route) {
        SignUpScreen(
            authViewModel = authViewModel,
            onNavigateBack = {
                navController.popBackStack()
            },
            onSignUpSuccess = onAuthSuccess
        )
    }
}
