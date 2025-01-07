package ro.si.finnsathii.ui.screens.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.si.finnsathii.data.model.Transaction
import ro.si.finnsathii.data.model.enums.TransactionType
import ro.si.finnsathii.ui.components.FinnSathiiButton
import ro.si.finnsathii.ui.components.FinnSathiiTextField
import ro.si.finnsathii.ui.components.FinnSathiiTopBar
import ro.si.finnsathii.ui.state.AuthState
import ro.si.finnsathii.ui.viewmodel.AuthViewModel
import ro.si.finnsathii.ui.viewmodel.TransactionViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onNavigateBack: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }
    var transactionType by remember { mutableStateOf(TransactionType.EXPENSE) }
    var isRecurring by remember { mutableStateOf(false) }

    val authState by authViewModel.authState.collectAsState()
    val userId = when (authState) {
        is AuthState.Authenticated -> (authState as AuthState.Authenticated).userId
        else -> null
    }

    Scaffold(
        topBar = {
            FinnSathiiTopBar(
                title = "Add Transaction",
                onBackClick = onNavigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Transaction Type Selection
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                FilterChip(
                    selected = transactionType == TransactionType.EXPENSE,
                    onClick = { transactionType = TransactionType.EXPENSE },
                    label = { Text("Expense") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(
                    selected = transactionType == TransactionType.INCOME,
                    onClick = { transactionType = TransactionType.INCOME },
                    label = { Text("Income") },
                    modifier = Modifier.weight(1f)
                )
            }

            FinnSathiiTextField(
                value = amount,
                onValueChange = { if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*\$"))) amount = it },
                label = "Amount",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FinnSathiiTextField(
                value = categoryId,
                onValueChange = { categoryId = it },
                label = "Category ID",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FinnSathiiTextField(
                value = description,
                onValueChange = { description = it },
                label = "Description",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Recurring Transaction",
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isRecurring,
                    onCheckedChange = { isRecurring = it }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            FinnSathiiButton(
                onClick = {
                    userId?.let { uid ->
                        val amountValue = amount.toDoubleOrNull() ?: 0.0
                        val transaction = Transaction(
                            amount = amountValue,
                            type = transactionType,
                            categoryId = categoryId.toLong(),
                            description = description,
                            date = Date(),
                            isRecurring = isRecurring,
                            userId = uid
                        )
                        viewModel.addTransaction(transaction)
                        viewModel.loadTransactions(uid) // Reload transactions after adding
                        onNavigateBack()
                    }
                },
                text = "Add Transaction",
                enabled = amount.isNotEmpty() && categoryId.isNotEmpty() && userId != null
            )
        }
    }
}
