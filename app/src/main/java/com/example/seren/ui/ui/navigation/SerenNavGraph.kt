package com.example.seren.ui.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seren.ui.ui.AddMoodScreen
import com.example.seren.ui.ui.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SerenNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "mood_list"
    ) {

        composable("mood_list") {
            MainScreen(navController)
        }

        composable("add_mood") {
            AddMoodScreen(navController)
        }

    }

}