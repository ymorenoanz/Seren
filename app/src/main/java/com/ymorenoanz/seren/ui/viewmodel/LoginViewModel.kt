package com.ymorenoanz.seren.ui.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ymorenoanz.seren.data.mapper.UserMap
import com.ymorenoanz.seren.ui.states.MoodUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(): ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val mapper = UserMap()

    private val _uiState = MutableStateFlow(MoodUIState())
    val uiState = _uiState.asStateFlow()

    fun handleSignInResult(data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            val credential = GoogleAuthProvider.getCredential(account.idToken,
                null)


            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val firebaseUser = auth.currentUser
                        val name = account.displayName
                            ?: firebaseUser?.email
                            ?: "User"

                        _uiState.update {
                            it.copy(
                                isLoggedIn = true,
                                userName = name.ifBlank { "User" }
                            )
                        }

                    } else {
                        Log.e("FIREBASE_ERROR", "Login failed", task.exception)
                    }
                }


        } catch (e: Exception) {
            _uiState.update {
                it.copy(error = "Login cancelled")
            }
        }
    }

}