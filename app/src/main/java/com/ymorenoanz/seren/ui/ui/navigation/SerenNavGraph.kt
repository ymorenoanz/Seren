package com.ymorenoanz.seren.ui.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ymorenoanz.seren.ui.ui.screen.AddMoodScreen
import com.ymorenoanz.seren.ui.ui.screen.LoginScreen
import com.ymorenoanz.seren.ui.ui.screen.MainScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SerenNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable(route = "login") {
            LoginScreen(navController)
        }

        //we pass the userName through the navigation
        composable("home/{userName}") { backStackEntry ->
            val rawName = backStackEntry.arguments?.getString("userName") ?: ""
            val userName = URLDecoder.decode(rawName, StandardCharsets.UTF_8.toString())

            MainScreen(navController = navController,
                userName = userName)
        }

        composable("add_mood") {
            AddMoodScreen(navController)
        }

    }

}