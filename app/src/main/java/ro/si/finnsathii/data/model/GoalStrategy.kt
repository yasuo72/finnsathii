package ro.si.finnsathii.data.model

import androidx.room.*
import ro.si.finnsathii.data.model.enums.StrategyDifficulty
import ro.si.finnsathii.data.model.enums.StrategyType

@Entity(
    tableName = "goal_strategies",
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["goalId"])
    ]
)
data class GoalStrategy(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val goalId: Long,
    val title: String,
    val description: String,
    val type: StrategyType,
    val difficulty: StrategyDifficulty,
    val potentialSavings: Double,
    val isActive: Boolean = true,
    val isCompleted: Boolean = false
)
