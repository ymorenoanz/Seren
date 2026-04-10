package com.ymorenoanz.seren.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.ymorenoanz.seren.domain.model.User

class UserMap {
    fun map(firebaseUser: FirebaseUser?): User {
        val name = firebaseUser?.displayName
            ?: firebaseUser?.email
            ?: "User"

        return User(
            name = firebaseUser?.displayName ?: "",
            email = firebaseUser?.email ?: ""
        )
    }
}
