package ro.si.finnsathii.ui.screens.achievements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.si.finnsathii.data.model.Achievement
import ro.si.finnsathii.data.model.Badge
import ro.si.finnsathii.data.model.Reward
import ro.si.finnsathii.data.model.enums.AchievementCategory
import ro.si.finnsathii.data.repository.AchievementsRepository
import ro.si.finnsathii.ui.state.AchievementsUiState
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    private val achievementsRepository: AchievementsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AchievementsUiState())
    val uiState: StateFlow<AchievementsUiState> = _uiState.asStateFlow()

    private var currentStreak = 0

    fun loadAchievementsData(userId: Long) = viewModelScope.launch {
        val totalSavings = achievementsRepository.getTotalSavings(userId)
        currentStreak = achievementsRepository.getCurrentStreak(userId)
        
        achievementsRepository.getAchievementsByCategory(userId, AchievementCategory.SAVINGS).collect { savingsAchievements ->
            achievementsRepository.getAchievementsByCategory(userId, AchievementCategory.TRANSACTIONS).collect { spendingAchievements ->
                val allAchievements = mutableListOf<Achievement>().apply {
                    addAll(spendingAchievements)
                }
                
                val streakAchievement = Achievement(
                    userId = userId,
                    category = AchievementCategory.STREAK,
                    title = "Daily Streak",
                    description = "Current streak: $currentStreak days",
                    target = 30,
                    progress = currentStreak
                )
                
                allAchievements.add(streakAchievement)
                
                _uiState.update { currentState ->
                    currentState.copy(
                        points = calculatePoints(allAchievements),
                        level = calculateLevel(totalSavings),
                        levelProgress = calculateLevelProgress(totalSavings),
                        recentBadges = emptyList(), // You might want to implement badge retrieval
                        activeAchievements = allAchievements
                    )
                }
            }
        }
    }

    private fun calculatePoints(achievements: List<Achievement>): Int {
        return achievements.sumOf { it.reward }
    }

    private fun calculateLevel(totalSavings: Double): Int {
        // Simple level calculation based on total savings
        return (totalSavings / 1000).toInt() + 1
    }

    private fun calculateLevelProgress(totalSavings: Double): Float {
        // Calculate progress within current level
        val level = calculateLevel(totalSavings)
        val baseSavings = (level - 1) * 1000
        val progressInCurrentLevel = totalSavings - baseSavings
        return (progressInCurrentLevel / 1000).toFloat().coerceIn(0f, 1f)
    }

    fun checkAndUpdateAchievements(userId: Long, category: AchievementCategory, progress: Int) = viewModelScope.launch {
        achievementsRepository.updateAchievementProgress(userId, category, progress)
        loadAchievementsData(userId)
    }
}
