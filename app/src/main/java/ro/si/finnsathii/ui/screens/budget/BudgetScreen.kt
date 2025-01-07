package ro.si.finnsathii.ui.screens.budget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.si.finnsathii.data.model.Budget
import ro.si.finnsathii.ui.components.FinnSathiiTopBar
import ro.si.finnsathii.ui.components.LoadingIndicator
import ro.si.finnsathii.ui.state.AuthState
import ro.si.finnsathii.ui.state.BudgetState
import ro.si.finnsathii.ui.viewmodel.AuthViewModel
import ro.si.finnsathii.ui.viewmodel.BudgetViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddBudget: () -> Unit = {},
    viewModel: BudgetViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val budgetState by viewModel.budgetState.collectAsState()
    val authState by authViewModel.authState.collectAsState()

    // Load budgets when user is authenticated
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                val userId = (authState as AuthState.Authenticated).userId
                viewModel.loadBudgets(userId)
            }
            is AuthState.Loading -> { /* Wait for auth state */ }
            is AuthState.Error -> { /* Handle error */ }
            is AuthState.Idle -> { /* Initial state */ }
        }
    }

    Scaffold(
        topBar = {
            FinnSathiiTopBar(
                title = "Budgets",
                onBackClick = onNavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddBudget,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Budget"
                )
            }
        }
    ) { padding ->
        when (budgetState) {
            is BudgetState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator()
                }
            }
            is BudgetState.Success -> {
                val budgets = (budgetState as BudgetState.Success).budgets
                if (budgets.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No budgets yet. Create your first budget!",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(budgets) { budget ->
                            BudgetItem(budget = budget)
                        }
                    }
                }
            }
            is BudgetState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (budgetState as BudgetState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetItem(budget: Budget) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = budget.category,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Budget",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "₹%.2f".format(budget.amount),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column {
                    Text(
                        text = "Spent",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "₹%.2f".format(budget.spent),
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (budget.spent > budget.amount) 
                            MaterialTheme.colorScheme.error 
                            else MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LinearProgressIndicator(
                progress = (budget.spent / budget.amount).toFloat().coerceIn(0f, 1f),
                modifier = Modifier.fillMaxWidth(),
                color = if (budget.spent > budget.amount) 
                    MaterialTheme.colorScheme.error 
                    else MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = SimpleDateFormat("MMM dd", Locale.getDefault()).let { sdf ->
                    "${sdf.format(budget.startDate)} - ${sdf.format(budget.endDate)}"
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
