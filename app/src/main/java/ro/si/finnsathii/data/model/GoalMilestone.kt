package ro.si.finnsathii.data.model

import androidx.room.*
import java.util.Date

@Entity(
    tableName = "goal_milestones",
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
data class GoalMilestone(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val goalId: Long,
    val title: String,
    val description: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val dueDate: Date,
    val isCompleted: Boolean = false,
    val completedDate: Date? = null
)
