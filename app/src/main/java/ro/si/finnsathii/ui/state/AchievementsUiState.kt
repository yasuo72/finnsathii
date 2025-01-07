package ro.si.finnsathii.ui.state

import ro.si.finnsathii.data.model.Achievement
import ro.si.finnsathii.data.model.Badge

data class AchievementsUiState(
    val points: Int = 0,
    val level: Int = 1,
    val levelProgress: Float = 0f,
    val recentBadges: List<Badge> = emptyList(),
    val activeAchievements: List<Achievement> = emptyList()
)
