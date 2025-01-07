package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.TransactionDao
import ro.si.finnsathii.data.mapper.toLocalModel
import ro.si.finnsathii.data.mapper.toRemoteModel
import ro.si.finnsathii.data.model.Transaction as RemoteTransaction
import ro.si.finnsathii.data.model.enums.TransactionType
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    fun getTransactions(userId: Long): Flow<List<RemoteTransaction>> {
        return transactionDao.getTransactions(userId).map { localTransactions ->
            localTransactions.map { it.toRemoteModel() }
        }
    }

    suspend fun getTransactionCount(userId: Long): Int {
        return transactionDao.getTransactionCount(userId)
    }

    suspend fun getTotalByType(userId: Long, type: TransactionType): Double {
        return transactionDao.getTotalByType(userId, type)
    }

    suspend fun insertTransaction(transaction: RemoteTransaction): Long {
        return transactionDao.insertTransaction(transaction.toLocalModel())
    }

    suspend fun updateTransaction(transaction: RemoteTransaction) {
        transactionDao.updateTransaction(transaction.toLocalModel())
    }

    suspend fun deleteTransaction(transaction: RemoteTransaction) {
        transactionDao.deleteTransaction(transaction.toLocalModel())
    }

    suspend fun deleteTransactionById(transactionId: Long) {
        transactionDao.deleteTransactionById(transactionId)
    }

    fun getTransactionsByCategory(userId: Long, categoryId: Long): Flow<List<RemoteTransaction>> {
        return transactionDao.getTransactionsByCategory(userId, categoryId)
            .map { localTransactions ->
                localTransactions.map { it.toRemoteModel() }
            }
    }

    suspend fun getTotalIncome(userId: Long): Double {
        return transactionDao.getTotalByType(userId, TransactionType.INCOME)
    }

    suspend fun getTotalExpense(userId: Long): Double {
        return transactionDao.getTotalByType(userId, TransactionType.EXPENSE)
    }
}
