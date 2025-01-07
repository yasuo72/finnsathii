package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val category: String,
    val amount: Double,
    val spent: Double = 0.0,
    val startDate: Date,
    val endDate: Date,
    val recurringPeriod: String? = null,
    val progress: Double = 0.0
)
