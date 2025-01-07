package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.Goal
import ro.si.finnsathii.data.model.enums.GoalCategory
import ro.si.finnsathii.data.model.enums.GoalStatus
import java.util.Date

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals WHERE userId = :userId")
    fun getGoalsByUser(userId: Long): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :goalId")
    fun getGoalById(goalId: Long): Flow<Goal>

    @Query("SELECT * FROM goals WHERE userId = :userId AND category = :category")
    fun getGoalsByCategory(userId: Long, category: GoalCategory): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE userId = :userId AND status = :status")
    fun getGoalsByStatus(userId: Long, status: GoalStatus): Flow<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("UPDATE goals SET currentAmount = :newAmount, lastUpdated = :updatedDate WHERE id = :goalId")
    suspend fun updateGoalProgress(goalId: Long, newAmount: Double, updatedDate: Date)

    @Query("SELECT COALESCE(SUM(currentAmount), 0.0) FROM goals WHERE userId = :userId")
    suspend fun getTotalGoalProgress(userId: Long): Double

    @Query("SELECT COALESCE(SUM(targetAmount), 0.0) FROM goals WHERE userId = :userId")
    suspend fun getTotalGoalTargetAmount(userId: Long): Double
}
