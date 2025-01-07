package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.BudgetDao
import ro.si.finnsathii.data.mapper.toLocalModel
import ro.si.finnsathii.data.mapper.toRemoteModel
import ro.si.finnsathii.data.model.Budget as RemoteBudget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepository @Inject constructor(
    private val budgetDao: BudgetDao
) {
    fun getAllBudgets(userId: Long): Flow<List<RemoteBudget>> {
        return budgetDao.getAllBudgets(userId).map { localBudgets ->
            localBudgets.map { it.toRemoteModel() }
        }
    }

    suspend fun insertBudget(budget: RemoteBudget): Long {
        return budgetDao.insertBudget(budget.toLocalModel())
    }

    suspend fun updateBudget(budget: RemoteBudget) {
        budgetDao.updateBudget(budget.toLocalModel())
    }

    suspend fun deleteBudget(budget: RemoteBudget) {
        budgetDao.deleteBudget(budget.toLocalModel())
    }

    fun getBudgetByCategory(userId: Long, category: String): Flow<RemoteBudget?> {
        return budgetDao.getBudgetByCategory(userId, category).map { it?.toRemoteModel() }
    }

    suspend fun updateBudgetSpent(budgetId: Long, amount: Double) {
        budgetDao.updateSpent(budgetId, amount)
        budgetDao.updateProgress(budgetId)
    }
}
