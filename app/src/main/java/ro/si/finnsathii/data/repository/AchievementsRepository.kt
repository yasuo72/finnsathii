package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.AchievementDao
import ro.si.finnsathii.data.local.dao.TransactionDao
import ro.si.finnsathii.data.local.dao.UserProgressDao
import ro.si.finnsathii.data.model.Achievement
import ro.si.finnsathii.data.model.enums.AchievementCategory
import ro.si.finnsathii.data.model.enums.TransactionType
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AchievementsRepository @Inject constructor(
    private val achievementDao: AchievementDao,
    private val transactionDao: TransactionDao,
    private val userProgressDao: UserProgressDao
) {
    suspend fun getTotalSavings(userId: Long): Double {
        return transactionDao.getTotalByType(userId, TransactionType.INCOME) -
               transactionDao.getTotalByType(userId, TransactionType.EXPENSE)
    }

    suspend fun getCurrentStreak(userId: Long): Int {
        return userProgressDao.getUserProgress(userId).first()?.streakDays ?: 0
    }

    suspend fun getTransactionCount(userId: Long): Int {
        return transactionDao.getTransactionCount(userId)
    }

    suspend fun getAchievementsByCategory(userId: Long, category: AchievementCategory): Flow<List<Achievement>> {
        return achievementDao.getAchievementsByCategory(userId, category).map { localAchievements ->
            localAchievements.map { it.toDomainModel() }
        }
    }

    suspend fun updateAchievementProgress(
        userId: Long, 
        category: AchievementCategory, 
        progress: Int
    ) {
        val achievement = achievementDao.getAchievementByCategory(userId, category).first()
        achievement?.let { existingAchievement ->
            val domainAchievement = existingAchievement.toDomainModel()
            val updatedProgress = minOf(domainAchievement.progress + progress, domainAchievement.target)
            achievementDao.updateAchievement(
                existingAchievement.copy(
                    progress = updatedProgress,
                    dateCompleted = if (updatedProgress >= domainAchievement.target) Date() else null
                )
            )
        }
    }
}

private fun ro.si.finnsathii.data.local.model.Achievement.toDomainModel(): Achievement {
    return Achievement(
        id = id,
        userId = userId,
        title = title,
        description = description,
        category = category,
        target = requirement,
        progress = progress,
        reward = points,
        dateCompleted = dateCompleted
    )
}
