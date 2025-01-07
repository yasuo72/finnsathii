package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ro.si.finnsathii.data.local.model.User
import ro.si.finnsathii.data.remote.FirebaseDataSource
import ro.si.finnsathii.ui.state.AuthState
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {
    fun login(email: String, password: String): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            val result = firebaseDataSource.signInWithEmail(email, password)
            result.user?.let { firebaseUser ->
                val user = User.fromFirebaseUser(
                    uid = firebaseUser.uid,
                    email = firebaseUser.email,
                    displayName = firebaseUser.displayName,
                    isEmailVerified = firebaseUser.isEmailVerified
                )
                emit(AuthState.Authenticated(user, user.id.hashCode().toLong()))
            } ?: emit(AuthState.Error("Login failed: User is null"))
        } catch (e: Exception) {
            emit(AuthState.Error("Login failed: ${e.message}"))
        }
    }

    fun signInWithGoogle(idToken: String): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            val result = firebaseDataSource.signInWithGoogle(idToken)
            result.user?.let { firebaseUser ->
                val user = User.fromFirebaseUser(
                    uid = firebaseUser.uid,
                    email = firebaseUser.email,
                    displayName = firebaseUser.displayName,
                    isEmailVerified = firebaseUser.isEmailVerified
                )
                emit(AuthState.Authenticated(user, user.id.hashCode().toLong()))
            } ?: emit(AuthState.Error("Google Sign-In failed: User is null"))
        } catch (e: Exception) {
            emit(AuthState.Error("Google Sign-In failed: ${e.message}"))
        }
    }

    fun signUp(email: String, password: String): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            val result = firebaseDataSource.signUpWithEmail(email, password)
            result.user?.let { firebaseUser ->
                val user = User.fromFirebaseUser(
                    uid = firebaseUser.uid,
                    email = firebaseUser.email,
                    displayName = firebaseUser.displayName,
                    isEmailVerified = firebaseUser.isEmailVerified
                )
                emit(AuthState.Authenticated(user, user.id.hashCode().toLong()))
            } ?: emit(AuthState.Error("Sign-up failed: User is null"))
        } catch (e: Exception) {
            emit(AuthState.Error("Sign-up failed: ${e.message}"))
        }
    }

    fun logout(): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            firebaseDataSource.signOut()
            emit(AuthState.Idle)
        } catch (e: Exception) {
            emit(AuthState.Error("Logout failed: ${e.message}"))
        }
    }

    fun getCurrentUser(): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            val currentUser = firebaseDataSource.currentUser
            if (currentUser != null) {
                val user = User.fromFirebaseUser(
                    uid = currentUser.uid,
                    email = currentUser.email,
                    displayName = currentUser.displayName,
                    isEmailVerified = currentUser.isEmailVerified
                )
                emit(AuthState.Authenticated(user, user.id.hashCode().toLong()))
            } else {
                emit(AuthState.Idle)
            }
        } catch (e: Exception) {
            emit(AuthState.Error("Failed to get current user: ${e.message}"))
        }
    }
}
