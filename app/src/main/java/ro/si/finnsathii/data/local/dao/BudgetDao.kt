package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.Budget

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budgets WHERE userId = :userId")
    fun getAllBudgets(userId: Long): Flow<List<Budget>>

    @Query("SELECT * FROM budgets WHERE id = :budgetId")
    fun getBudgetById(budgetId: Long): Flow<Budget>

    @Query("SELECT * FROM budgets WHERE userId = :userId AND category = :category LIMIT 1")
    fun getBudgetByCategory(userId: Long, category: String): Flow<Budget?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget): Long

    @Update
    suspend fun updateBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)

    @Query("UPDATE budgets SET spent = spent + :amount WHERE id = :budgetId")
    suspend fun updateSpent(budgetId: Long, amount: Double)

    @Query("UPDATE budgets SET progress = (spent / amount) * 100 WHERE id = :budgetId")
    suspend fun updateProgress(budgetId: Long)

    @Query("SELECT IFNULL(SUM(spent), 0.0) FROM budgets WHERE userId = :userId")
    suspend fun getTotalSpent(userId: Long): Double

    @Query("SELECT IFNULL(SUM(amount), 0.0) FROM budgets WHERE userId = :userId")
    suspend fun getTotalBudgetAmount(userId: Long): Double
}
