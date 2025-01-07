package ro.si.finnsathii.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddTransaction: () -> Unit,
    onNavigateToBudgets: () -> Unit,
    onTransactionClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Finnsathii",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToAddTransaction,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Transaction")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToBudgets,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Budgets")
        }

        // Example transaction list - replace with actual transaction list later
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Example transaction item
        Button(
            onClick = { onTransactionClick(1L) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sample Transaction")
        }
    }
}
