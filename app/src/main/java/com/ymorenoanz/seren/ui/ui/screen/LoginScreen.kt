package com.ymorenoanz.seren.ui.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ymorenoanz.seren.ui.viewmodel.MoodViewModel
import com.ymorenoanz.seren.BuildConfig
import com.ymorenoanz.seren.data.mapper.UserMap
import com.ymorenoanz.seren.ui.viewmodel.LoginViewModel
import com.ymorenoanz.seren.ui.viewmodel.SessionViewModel
import java.net.URLEncoder

@Composable
fun LoginScreen(navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()) {

    // Access the variable
    val webClientId = BuildConfig.WEB_CLIENT

    //Creating the Google client
    val context = LocalContext.current

    val googleSignInClient = remember {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        GoogleSignIn.getClient(context, options)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        viewModel.handleSignInResult(data)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                launcher.launch(googleSignInClient.signInIntent)
            }
        ) {
            Text("Sign in with Google 🌙")
        }
    }
}