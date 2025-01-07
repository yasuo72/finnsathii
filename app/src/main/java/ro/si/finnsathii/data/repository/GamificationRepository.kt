package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.first
import ro.si.finnsathii.data.local.dao.AchievementDao
import ro.si.finnsathii.data.local.dao.UserProgressDao
import ro.si.finnsathii.data.model.Achievement
import ro.si.finnsathii.data.model.enums.AchievementCategory
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamificationRepository @Inject constructor(
    private val achievementDao: AchievementDao,
    private val userProgressDao: UserProgressDao
) {
    suspend fun updateAchievementProgress(
        userId: Long, 
        category: AchievementCategory, 
        progress: Int
    ) {
        val achievement = achievementDao.getAchievementByCategory(userId, category).first()
        achievement?.let { localAchievement ->
            val domainAchievement = localAchievement.toDomainModel()
            val updatedProgress = minOf(domainAchievement.progress + progress, domainAchievement.target)
            achievementDao.updateAchievement(
                localAchievement.copy(
                    progress = updatedProgress,
                    dateCompleted = if (updatedProgress >= domainAchievement.target) Date() else null
                )
            )
        }
    }

    suspend fun addXp(userId: Long, xpAmount: Long) {
        userProgressDao.addXp(userId, xpAmount)
    }

    suspend fun incrementStreak(userId: Long) {
        userProgressDao.incrementStreak(userId)
    }

    suspend fun createAchievement(achievement: Achievement) {
        achievementDao.insertAchievement(achievement.toLocalModel())
    }

    suspend fun getAchievementProgress(userId: Long, category: AchievementCategory): Int {
        return achievementDao.getAchievementByCategory(userId, category).first()?.progress ?: 0
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

private fun Achievement.toLocalModel(): ro.si.finnsathii.data.local.model.Achievement {
    return ro.si.finnsathii.data.local.model.Achievement(
        id = id,
        userId = userId,
        title = title,
        description = description,
        category = category,
        requirement = target,
        points = reward,
        progress = progress,
        dateCompleted = dateCompleted
    )
}
