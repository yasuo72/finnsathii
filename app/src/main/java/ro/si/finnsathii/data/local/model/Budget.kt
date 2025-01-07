package ro.si.finnsathii.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "userId")
    val userId: Long,
    
    @ColumnInfo(name = "category")
    val category: String,
    
    @ColumnInfo(name = "amount")
    val amount: Double,
    
    @ColumnInfo(name = "spent")
    val spent: Double = 0.0,
    
    @ColumnInfo(name = "startDate")
    val startDate: Date,
    
    @ColumnInfo(name = "endDate")
    val endDate: Date,
    
    @ColumnInfo(name = "recurringPeriod")
    val recurringPeriod: String? = null,
    
    @ColumnInfo(name = "progress")
    val progress: Double = 0.0
)
