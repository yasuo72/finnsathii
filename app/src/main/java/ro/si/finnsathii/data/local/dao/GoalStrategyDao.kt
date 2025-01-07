package ro.si.finnsathii.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.model.GoalStrategy

@Dao
interface GoalStrategyDao {
    @Query("SELECT * FROM goal_strategies WHERE goalId = :goalId ORDER BY id ASC")
    fun getStrategiesForGoal(goalId: Long): Flow<List<GoalStrategy>>

    @Query("SELECT * FROM goal_strategies WHERE goalId = :goalId AND isActive = :isActive ORDER BY id ASC")
    fun getActiveStrategies(goalId: Long, isActive: Boolean = true): Flow<List<GoalStrategy>>

    @Query("SELECT * FROM goal_strategies WHERE goalId = :goalId AND isCompleted = :isCompleted ORDER BY id ASC")
    fun getStrategiesByStatus(goalId: Long, isCompleted: Boolean): Flow<List<GoalStrategy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStrategy(strategy: GoalStrategy)

    @Update
    suspend fun updateStrategy(strategy: GoalStrategy)

    @Delete
    suspend fun deleteStrategy(strategy: GoalStrategy)

    @Query("DELETE FROM goal_strategies WHERE goalId = :goalId")
    suspend fun deleteStrategiesForGoal(goalId: Long)

    @Query("""
        UPDATE goal_strategies 
        SET isActive = :isActive 
        WHERE id = :strategyId
    """)
    suspend fun setStrategyActive(strategyId: Long, isActive: Boolean)

    @Query("""
        UPDATE goal_strategies 
        SET isCompleted = :isCompleted 
        WHERE id = :strategyId
    """)
    suspend fun setStrategyCompleted(strategyId: Long, isCompleted: Boolean)
}
