package com.ymorenoanz.seren.ui.states

sealed class SessionState {
    object LoggedIn: SessionState()
    object LoggedOut: SessionState()
    object Loading: SessionState()
}