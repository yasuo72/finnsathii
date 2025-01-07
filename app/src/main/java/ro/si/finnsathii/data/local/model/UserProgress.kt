package ro.si.finnsathii.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey val userId: Long,
    val streakDays: Int = 0,
    val currentXp: Long = 0,
    val totalSavings: Double = 0.0,
    val totalTransactions: Int = 0
)
