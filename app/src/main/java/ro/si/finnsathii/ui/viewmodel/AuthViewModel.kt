package ro.si.finnsathii.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ro.si.finnsathii.data.repository.AuthRepository
import ro.si.finnsathii.ui.state.AuthState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkCurrentUser()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collectLatest { state ->
                _authState.value = state
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            authRepository.signInWithGoogle(idToken).collectLatest { state ->
                _authState.value = state
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(email, password).collectLatest { state ->
                _authState.value = state
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout().collectLatest { state ->
                _authState.value = state
            }
        }
    }

    private fun checkCurrentUser() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collectLatest { state ->
                _authState.value = state
            }
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
}
