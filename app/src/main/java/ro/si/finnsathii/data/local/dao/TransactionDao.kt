package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.Transaction
import ro.si.finnsathii.data.model.enums.TransactionType

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE userId = :userId")
    fun getTransactions(userId: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE userId = :userId AND categoryId = :categoryId")
    fun getTransactionsByCategory(userId: Long, categoryId: Long): Flow<List<Transaction>>

    @Query("SELECT COUNT(*) FROM transactions WHERE userId = :userId")
    suspend fun getTransactionCount(userId: Long): Int

    @Query("SELECT COALESCE(SUM(amount), 0.0) FROM transactions WHERE userId = :userId AND type = :type")
    suspend fun getTotalByType(userId: Long, type: TransactionType): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteTransactionById(transactionId: Long)
}
