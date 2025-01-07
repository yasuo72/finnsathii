package ro.si.finnsathii.data.model

import ro.si.finnsathii.data.model.enums.TransactionType
import java.util.Date

data class Transaction(
    val id: Long = 0,
    val userId: Long,
    val amount: Double,
    val description: String? = null,
    val date: Date,
    val type: TransactionType,
    val categoryId: Long,
    val isRecurring: Boolean = false
)
