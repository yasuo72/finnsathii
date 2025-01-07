package ro.si.finnsathii.ui.components

import androidx.compose.runtime.Composable
import ro.si.finnsathii.data.model.Transaction
import ro.si.finnsathii.data.model.enums.TransactionType

@Composable
fun TransactionItem(
    transaction: Transaction,
    onTransactionClick: (Transaction) -> Unit
) {
    val transactionColor = when (transaction.type) {
        TransactionType.INCOME -> androidx.compose.ui.graphics.Color.Green
        TransactionType.EXPENSE -> androidx.compose.ui.graphics.Color.Red
        TransactionType.TRANSFER -> androidx.compose.ui.graphics.Color.Blue
    }

    // Implement transaction item UI here
    // Use transaction properties like amount, description, timestamp
    // Use transactionColor for visual indication
}
