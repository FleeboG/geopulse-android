package com.geopulse.android.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geopulse.android.auth.AuthViewModel
import com.geopulse.android.auth.LoginScreen
import com.geopulse.android.auth.RegisterScreen
import com.geopulse.android.storage.TokenStore
import com.geopulse.android.zones.ZonesScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val tokenStore = TokenStore(context)

    val authViewModel: AuthViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                AuthViewModel(tokenStore = tokenStore)
            }
        }
    )

    val token by authViewModel.token.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onGoToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME) {
            val currentToken = token

            if (currentToken == null) {
                Text("Missing token. Please log in again.")
            } else {
                ZonesScreen(token = currentToken)
            }
        }
    }
}