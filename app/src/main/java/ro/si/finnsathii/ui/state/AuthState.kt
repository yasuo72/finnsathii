package ro.si.finnsathii.ui.state

import ro.si.finnsathii.data.local.model.User

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val user: User, val userId: Long) : AuthState()
    data class Error(val message: String) : AuthState()
}
