package ro.si.finnsathii.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GamificationViewModel @Inject constructor() : ViewModel() {
    private val _level = MutableStateFlow(1)
    val level: StateFlow<Int> = _level

    private val _experience = MutableStateFlow(75)  // Out of 100
    val experience: StateFlow<Int> = _experience

    private val _streakDays = MutableStateFlow(5)
    val streakDays: StateFlow<Int> = _streakDays

    private val _achievements = MutableStateFlow(listOf(
        Achievement("Saver Starter", "Save your first ₹1,000", true, 100),
        Achievement("Budget Master", "Create and stick to a budget for 1 month", false, 200),
        Achievement("Expense Tracker", "Track expenses for 7 consecutive days", true, 150),
        Achievement("Goal Getter", "Achieve your first savings goal", false, 300),
        Achievement("Smart Spender", "Stay under budget for 3 months", false, 500),
        Achievement("Money Maestro", "Complete all financial goals for a month", false, 1000)
    ))
    val achievements: StateFlow<List<Achievement>> = _achievements

    // Calculate total XP from unlocked achievements
    fun getTotalXP(): Int {
        return _achievements.value
            .filter { it.unlocked }
            .sumOf { it.xpPoints }
    }

    // Calculate progress to next level (0-100)
    fun getLevelProgress(): Int {
        return _experience.value
    }
}

data class Achievement(
    val title: String,
    val description: String,
    val unlocked: Boolean = false,
    val xpPoints: Int = 100
)
