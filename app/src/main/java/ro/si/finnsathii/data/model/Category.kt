package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ro.si.finnsathii.data.model.enums.TransactionType
import ro.si.finnsathii.data.util.Converters

@Entity(tableName = "categories")
@TypeConverters(Converters::class)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val name: String,
    val transactionType: TransactionType,
    val icon: String? = null,
    val color: String? = null,
    val isDefault: Boolean = false
)
