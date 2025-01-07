package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ro.si.finnsathii.data.model.enums.GoalType
import java.util.Date

@Entity(tableName = "goal_suggestions")
data class GoalSuggestion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String,
    val type: GoalType,
    val suggestedAmount: Double,
    val suggestedDuration: Int, // in days
    val isAccepted: Boolean = false,
    val createdAt: Date = Date(),
    val acceptedAt: Date? = null
)
