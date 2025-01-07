package ro.si.finnsathii.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.si.finnsathii.data.model.Budget
import ro.si.finnsathii.data.repository.BudgetRepository
import ro.si.finnsathii.ui.state.BudgetState
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    private val _budgetState = MutableStateFlow<BudgetState>(BudgetState.Loading)
    val budgetState: StateFlow<BudgetState> = _budgetState.asStateFlow()

    fun loadBudgets(userId: Long) {
        viewModelScope.launch {
            try {
                budgetRepository.getAllBudgets(userId).collect { budgets ->
                    _budgetState.update { BudgetState.Success(budgets) }
                }
            } catch (e: Exception) {
                _budgetState.update { BudgetState.Error(e.message ?: "Unknown error") }
            }
        }
    }

    fun addBudget(
        userId: Long,
        category: String,
        amount: Double,
        startDate: Date,
        endDate: Date,
        recurringPeriod: String? = null
    ) {
        viewModelScope.launch {
            try {
                val budget = Budget(
                    userId = userId,
                    category = category,
                    amount = amount,
                    startDate = startDate,
                    endDate = endDate,
                    recurringPeriod = recurringPeriod
                )
                budgetRepository.insertBudget(budget)
                loadBudgets(userId)
            } catch (e: Exception) {
                _budgetState.update { BudgetState.Error(e.message ?: "Error adding budget") }
            }
        }
    }

    fun updateBudget(budget: Budget) {
        viewModelScope.launch {
            try {
                budgetRepository.updateBudget(budget)
                loadBudgets(budget.userId)
            } catch (e: Exception) {
                _budgetState.update { BudgetState.Error(e.message ?: "Error updating budget") }
            }
        }
    }

    fun deleteBudget(budget: Budget) {
        viewModelScope.launch {
            try {
                budgetRepository.deleteBudget(budget)
                loadBudgets(budget.userId)
            } catch (e: Exception) {
                _budgetState.update { BudgetState.Error(e.message ?: "Error deleting budget") }
            }
        }
    }

    fun updateBudgetSpent(budgetId: Long, amount: Double, userId: Long) {
        viewModelScope.launch {
            try {
                budgetRepository.updateBudgetSpent(budgetId, amount)
                loadBudgets(userId)
            } catch (e: Exception) {
                _budgetState.update { BudgetState.Error(e.message ?: "Error updating budget spent") }
            }
        }
    }
}
