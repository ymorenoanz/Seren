package com.ymorenoanz.seren.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ymorenoanz.seren.ui.states.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SessionViewModel@Inject constructor(): ViewModel() {

    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Loading)
    val sessionState = _sessionState.asStateFlow()

    private val auth = FirebaseAuth.getInstance()

    private val listener = FirebaseAuth.AuthStateListener{ firebaseAuth ->
        val user = firebaseAuth.currentUser
        _sessionState.value =
            if (user != null) SessionState.LoggedIn
            else SessionState.LoggedOut
    }

    val userName: String
        get() = FirebaseAuth.getInstance().currentUser?.displayName ?: "User"

    init {
     auth.addAuthStateListener(listener)
    }

    fun logout(){
        auth.signOut()
        _sessionState.value = SessionState.LoggedOut
    }

    override fun onCleared() {
        super.onCleared()
        auth.removeAuthStateListener(listener)
    }

}