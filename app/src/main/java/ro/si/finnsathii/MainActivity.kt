package ro.si.finnsathii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ro.si.finnsathii.navigation.NavigationRoutes
import ro.si.finnsathii.ui.screens.auth.LoginScreen
import ro.si.finnsathii.ui.screens.auth.SignUpScreen
import ro.si.finnsathii.ui.screens.home.HomeScreen
import ro.si.finnsathii.ui.theme.FinnsathiiTheme
import ro.si.finnsathii.ui.viewmodel.AuthViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinnsathiiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(authViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBottomBar = remember(currentRoute) {
        when (currentRoute) {
            NavigationRoutes.LOGIN, NavigationRoutes.SIGNUP -> false
            else -> true
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == NavigationRoutes.HOME,
                        onClick = {
                            navController.navigate(NavigationRoutes.HOME) {
                                popUpTo(NavigationRoutes.HOME) { inclusive = true }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.AccountBalance, contentDescription = "Budgets") },
                        label = { Text("Budgets") },
                        selected = currentRoute == NavigationRoutes.BUDGETS,
                        onClick = {
                            navController.navigate(NavigationRoutes.BUDGETS) {
                                popUpTo(NavigationRoutes.HOME)
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = currentRoute == NavigationRoutes.PROFILE,
                        onClick = {
                            navController.navigate(NavigationRoutes.PROFILE) {
                                popUpTo(NavigationRoutes.HOME)
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.LOGIN,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationRoutes.LOGIN) {
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToSignUp = {
                        navController.navigate(NavigationRoutes.SIGNUP)
                    },
                    onLoginSuccess = {
                        navController.navigate(NavigationRoutes.HOME) {
                            popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }

            composable(NavigationRoutes.SIGNUP) {
                SignUpScreen(
                    authViewModel = authViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onSignUpSuccess = {
                        navController.navigate(NavigationRoutes.HOME) {
                            popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }

            composable(NavigationRoutes.HOME) {
                HomeScreen(
                    onNavigateToAddTransaction = {
                        navController.navigate(NavigationRoutes.ADD_TRANSACTION)
                    },
                    onNavigateToBudgets = {
                        navController.navigate(NavigationRoutes.BUDGETS)
                    },
                    onTransactionClick = { transactionId ->
                        navController.navigate("${NavigationRoutes.TRANSACTION_DETAILS}/$transactionId")
                    }
                )
            }

            composable(
                route = "${NavigationRoutes.TRANSACTION_DETAILS}/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.LongType })
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Transaction Details Coming Soon")
                }
            }

            composable(NavigationRoutes.ADD_TRANSACTION) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Add Transaction Coming Soon")
                }
            }

            composable(NavigationRoutes.BUDGETS) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Budgets Coming Soon")
                }
            }

            composable(NavigationRoutes.PROFILE) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Profile Coming Soon")
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
