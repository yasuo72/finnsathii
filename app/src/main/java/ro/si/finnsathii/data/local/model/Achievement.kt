package ro.si.finnsathii.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ro.si.finnsathii.data.model.enums.AchievementCategory
import java.util.Date

@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String,
    val points: Int,
    val category: AchievementCategory,
    val requirement: Int, // The value needed to unlock this achievement
    val progress: Int = 0,
    val dateCompleted: Date? = null
)
