package ro.si.finnsathii.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.model.GoalMilestone
import java.util.Date

@Dao
interface GoalMilestoneDao {
    @Query("SELECT * FROM goal_milestones WHERE goalId = :goalId ORDER BY dueDate ASC")
    fun getMilestonesForGoal(goalId: Long): Flow<List<GoalMilestone>>

    @Query("SELECT * FROM goal_milestones WHERE goalId = :goalId AND isCompleted = :isCompleted ORDER BY dueDate ASC")
    fun getMilestonesByStatus(goalId: Long, isCompleted: Boolean): Flow<List<GoalMilestone>>

    @Query("SELECT * FROM goal_milestones WHERE id = :milestoneId")
    fun getMilestoneById(milestoneId: Long): Flow<GoalMilestone>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMilestone(milestone: GoalMilestone)

    @Update
    suspend fun updateMilestone(milestone: GoalMilestone)

    @Delete
    suspend fun deleteMilestone(milestone: GoalMilestone)

    @Query("DELETE FROM goal_milestones WHERE goalId = :goalId")
    suspend fun deleteMilestonesForGoal(goalId: Long)

    @Query("""
        UPDATE goal_milestones 
        SET currentAmount = currentAmount + :amount 
        WHERE id = :milestoneId
    """)
    suspend fun updateMilestoneProgress(milestoneId: Long, amount: Double)
}
