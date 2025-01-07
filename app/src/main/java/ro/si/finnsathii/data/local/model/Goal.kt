package ro.si.finnsathii.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ro.si.finnsathii.data.model.enums.GoalCategory
import ro.si.finnsathii.data.model.enums.GoalStatus
import ro.si.finnsathii.data.model.enums.GoalTimeframe
import java.util.Date

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "userId")
    val userId: Long,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "description")
    val description: String? = null,
    
    @ColumnInfo(name = "targetAmount")
    val targetAmount: Double,
    
    @ColumnInfo(name = "currentAmount")
    val currentAmount: Double = 0.0,
    
    @ColumnInfo(name = "startDate")
    val startDate: Date,
    
    @ColumnInfo(name = "endDate")
    val endDate: Date,
    
    @ColumnInfo(name = "category")
    val category: GoalCategory,
    
    @ColumnInfo(name = "timeframe")
    val timeframe: GoalTimeframe,
    
    @ColumnInfo(name = "status")
    val status: GoalStatus = GoalStatus.NOT_STARTED,
    
    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: Date = Date()
)
