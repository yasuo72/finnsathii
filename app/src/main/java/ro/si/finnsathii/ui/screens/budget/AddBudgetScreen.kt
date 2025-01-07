package ro.si.finnsathii.ui.screens.budget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.si.finnsathii.ui.state.AuthState
import androidx.hilt.navigation.compose.hiltViewModel
import ro.si.finnsathii.data.model.Budget
import ro.si.finnsathii.ui.components.FinnSathiiButton
import ro.si.finnsathii.ui.components.FinnSathiiTextField
import ro.si.finnsathii.ui.components.FinnSathiiTopBar
import ro.si.finnsathii.ui.viewmodel.AuthViewModel
import ro.si.finnsathii.ui.viewmodel.BudgetViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBudgetScreen(
    onNavigateBack: () -> Unit,
    viewModel: BudgetViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var category by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf(Date()) }
    var endDate by remember { mutableStateOf(Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)) } // 30 days from now
    var selectedCategory by remember { mutableStateOf("") }

    val authState by authViewModel.authState.collectAsState()
    val userId = when (authState) {
        is AuthState.Authenticated -> (authState as AuthState.Authenticated).userId
        else -> null
    }

    Scaffold(
        topBar = {
            FinnSathiiTopBar(
                title = "Add Budget",
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
            FinnSathiiTextField(
                value = category,
                onValueChange = { category = it },
                label = "Category",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FinnSathiiTextField(
                value = amount,
                onValueChange = { if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*\$"))) amount = it },
                label = "Amount",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // TODO: Add date pickers for start and end dates

            Spacer(modifier = Modifier.weight(1f))

            FinnSathiiButton(
                onClick = {
                    userId?.let { uid ->
                        val amountValue = amount.toDoubleOrNull() ?: 0.0
                        viewModel.addBudget(
                            category = selectedCategory,
                            amount = amountValue,
                            startDate = startDate,
                            endDate = endDate,
                            userId = uid
                        )
                        onNavigateBack()
                    }
                },
                text = "Add Budget",
                enabled = category.isNotEmpty() && amount.isNotEmpty() && userId != null
            )
        }
    }
}
