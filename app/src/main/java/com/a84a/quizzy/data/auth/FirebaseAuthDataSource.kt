package com.a84a.quizzy.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    suspend fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun isLoggedIn(): Boolean = firebaseAuth.currentUser != null
}
