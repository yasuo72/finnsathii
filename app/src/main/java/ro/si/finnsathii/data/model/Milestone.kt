package ro.si.finnsathii.data.model

import java.util.Date

data class Milestone(
    val title: String,
    val description: String,
    val targetAmount: Double,
    val targetDate: Date,
    val isCompleted: Boolean = false,
    val completedDate: Date? = null,
    val reward: GoalReward? = null
)

data class GoalReward(
    val type: GoalRewardType,
    val value: Int,
    val description: String
)

enum class GoalRewardType {
    XP_POINTS,
    ACHIEVEMENT_BADGE,
    STREAK_BONUS,
    SPECIAL_THEME,
    CUSTOM_REWARD
}
