package ro.si.finnsathii.ui.state

import ro.si.finnsathii.data.model.Transaction

sealed class TransactionState {
    object Loading : TransactionState()
    data class Success(val transactions: List<Transaction> = emptyList()) : TransactionState()
    data class Error(val message: String) : TransactionState()
}
