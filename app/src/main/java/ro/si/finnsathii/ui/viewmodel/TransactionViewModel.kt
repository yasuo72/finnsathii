package ro.si.finnsathii.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.si.finnsathii.data.model.Transaction
import ro.si.finnsathii.data.model.enums.TransactionType
import ro.si.finnsathii.data.repository.TransactionRepository
import ro.si.finnsathii.ui.state.TransactionState
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _transactionState = MutableStateFlow<TransactionState>(TransactionState.Loading)
    val transactionState: StateFlow<TransactionState> = _transactionState.asStateFlow()

    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome.asStateFlow()

    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense.asStateFlow()

    fun loadTransactions(userId: Long) {
        viewModelScope.launch {
            _transactionState.value = TransactionState.Loading
            try {
                // Load totals first
                _totalIncome.value = transactionRepository.getTotalByType(userId, TransactionType.INCOME)
                _totalExpense.value = transactionRepository.getTotalByType(userId, TransactionType.EXPENSE)
                
                // Then load transactions
                transactionRepository.getTransactions(userId).collect { transactions ->
                    if (transactions.isEmpty()) {
                        _transactionState.value = TransactionState.Success(emptyList())
                    } else {
                        _transactionState.value = TransactionState.Success(transactions)
                    }
                }
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                transactionRepository.insertTransaction(transaction)
                // Reload transactions after adding
                loadTransactions(transaction.userId)
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Failed to add transaction")
            }
        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                transactionRepository.updateTransaction(transaction)
                loadTransactions(transaction.userId)
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Error updating transaction")
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                transactionRepository.deleteTransaction(transaction)
                loadTransactions(transaction.userId)
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Error deleting transaction")
            }
        }
    }

    fun getTransactionsByCategory(userId: Long, categoryId: Long) {
        viewModelScope.launch {
            try {
                transactionRepository.getTransactionsByCategory(userId, categoryId).collect { transactions ->
                    _transactionState.value = TransactionState.Success(transactions)
                }
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Error fetching transactions")
            }
        }
    }
}
