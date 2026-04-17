package com.ymorenoanz.seren.ui.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ymorenoanz.seren.ui.states.SessionState
import com.ymorenoanz.seren.ui.ui.screen.AddMoodScreen
import com.ymorenoanz.seren.ui.ui.screen.LoginScreen
import com.ymorenoanz.seren.ui.ui.screen.MainScreen
import com.ymorenoanz.seren.ui.viewmodel.MoodViewModel
import com.ymorenoanz.seren.ui.viewmodel.SessionViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SerenNavGraph(
    viewModel: SessionViewModel = hiltViewModel()) {

    val state by viewModel.sessionState.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(state) {
        when (state) {
            SessionState.LoggedIn -> {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }

            SessionState.LoggedOut -> {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                    launchSingleTop = true
                }
            }

            SessionState.Loading -> {
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            LoginScreen(navController)
        }

        composable("home") {
            MainScreen(
                navController = navController,
                sessionViewModel = viewModel)
        }

        composable("add_mood") {
            AddMoodScreen(navController)
        }

    }
}