package ro.si.finnsathii.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    val currentUser get() = auth.currentUser

    suspend fun signInWithGoogle(idToken: String) = try {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).await()
    } catch (e: Exception) {
        throw e
    }

    suspend fun signInWithEmail(email: String, password: String) = try {
        auth.signInWithEmailAndPassword(email, password).await()
    } catch (e: Exception) {
        throw e
    }

    suspend fun signUpWithEmail(email: String, password: String) = try {
        auth.createUserWithEmailAndPassword(email, password).await()
    } catch (e: Exception) {
        throw e
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun resetPassword(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
    } catch (e: Exception) {
        throw e
    }
}
