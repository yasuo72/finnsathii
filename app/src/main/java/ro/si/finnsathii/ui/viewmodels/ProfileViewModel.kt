package ro.si.finnsathii.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    
    val userEmail: String?
        get() = auth.currentUser?.email

    fun signOut() {
        auth.signOut()
    }
}
