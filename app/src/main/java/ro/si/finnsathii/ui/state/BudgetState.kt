package ro.si.finnsathii.ui.state

import ro.si.finnsathii.data.model.Budget

sealed class BudgetState {
    object Loading : BudgetState()
    data class Success(val budgets: List<Budget> = emptyList()) : BudgetState()
    data class Error(val message: String) : BudgetState()
}
