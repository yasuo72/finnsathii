package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ro.si.finnsathii.data.model.enums.GoalCategory
import ro.si.finnsathii.data.model.enums.GoalStatus
import ro.si.finnsathii.data.model.enums.GoalTimeframe
import ro.si.finnsathii.data.util.Converters
import java.util.Date

@Entity(tableName = "goals")
@TypeConverters(Converters::class)
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String? = null,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val startDate: Date,
    val endDate: Date,
    val category: GoalCategory,
    val timeframe: GoalTimeframe,
    val status: GoalStatus = GoalStatus.NOT_STARTED,
    val lastUpdated: Date = Date()
)
