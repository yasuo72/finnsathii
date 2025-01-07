package ro.si.finnsathii.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import ro.si.finnsathii.data.model.enums.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) 
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "userId")
    val userId: Long,
    
    @ColumnInfo(name = "amount")
    val amount: Double,
    
    @ColumnInfo(name = "type")
    val type: TransactionType,
    
    @ColumnInfo(name = "categoryId")
    val categoryId: Long,
    
    @ColumnInfo(name = "description")
    val description: String? = null,
    
    @ColumnInfo(name = "date")
    val date: Date,
    
    @ColumnInfo(name = "isRecurring")
    val isRecurring: Boolean = false
)
