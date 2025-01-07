package ro.si.finnsathii.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ro.si.finnsathii.data.model.enums.TransactionType

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "userId")
    val userId: Long,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "transactionType")
    val transactionType: TransactionType,
    
    @ColumnInfo(name = "icon")
    val icon: String? = null,
    
    @ColumnInfo(name = "color")
    val color: String? = null,
    
    @ColumnInfo(name = "isDefault")
    val isDefault: Boolean = false
)
