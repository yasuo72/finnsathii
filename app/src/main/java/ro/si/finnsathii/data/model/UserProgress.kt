package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey
    val userId: Long,
    val level: Int = 1,
    val xpEarned: Int = 0,
    val totalXpEarned: Int = 0,
    val currentStreak: Int = 0,
    val highestStreak: Int = 0,
    val lastStreakUpdate: Date? = null,
    val xpBoostActive: Boolean = false,
    val xpBoostEndTime: Date? = null,
    val streakShieldActive: Boolean = false,
    val activeTheme: String = "default"
)
