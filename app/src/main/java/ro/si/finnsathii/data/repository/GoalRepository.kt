package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.GoalDao
import ro.si.finnsathii.data.mapper.toLocalModel
import ro.si.finnsathii.data.mapper.toRemoteModel
import ro.si.finnsathii.data.model.Goal as RemoteGoal
import ro.si.finnsathii.data.model.enums.GoalCategory
import ro.si.finnsathii.data.model.enums.GoalStatus
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepository @Inject constructor(
    private val goalDao: GoalDao
) {
    fun getGoalsByUser(userId: Long): Flow<List<RemoteGoal>> {
        return goalDao.getGoalsByUser(userId).map { localGoals ->
            localGoals.map { it.toRemoteModel() }
        }
    }

    fun getGoalById(goalId: Long): Flow<RemoteGoal?> {
        return goalDao.getGoalById(goalId).map { it?.toRemoteModel() }
    }

    fun getGoalsByCategory(userId: Long, category: GoalCategory): Flow<List<RemoteGoal>> {
        return goalDao.getGoalsByCategory(userId, category).map { localGoals ->
            localGoals.map { it.toRemoteModel() }
        }
    }

    fun getGoalsByStatus(userId: Long, status: GoalStatus): Flow<List<RemoteGoal>> {
        return goalDao.getGoalsByStatus(userId, status).map { localGoals ->
            localGoals.map { it.toRemoteModel() }
        }
    }

    suspend fun insertGoal(goal: RemoteGoal): Long {
        return goalDao.insertGoal(goal.toLocalModel())
    }

    suspend fun updateGoal(goal: RemoteGoal) {
        goalDao.updateGoal(goal.toLocalModel())
    }

    suspend fun deleteGoal(goal: RemoteGoal) {
        goalDao.deleteGoal(goal.toLocalModel())
    }

    suspend fun updateGoalProgress(goalId: Long, amount: Double, updatedDate: Date) {
        goalDao.updateGoalProgress(goalId, amount, updatedDate)
    }

    suspend fun getTotalGoalProgress(userId: Long): Double {
        return goalDao.getTotalGoalProgress(userId)
    }

    suspend fun getTotalGoalTargetAmount(userId: Long): Double {
        return goalDao.getTotalGoalTargetAmount(userId)
    }
}
