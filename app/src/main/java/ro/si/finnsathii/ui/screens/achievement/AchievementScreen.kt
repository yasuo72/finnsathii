package ro.si.finnsathii.ui.screens.achievement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ro.si.finnsathii.ui.components.FinnSathiiTopBar
import ro.si.finnsathii.ui.viewmodel.GamificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(
    viewModel: GamificationViewModel = hiltViewModel()
) {
    val achievements by viewModel.achievements.collectAsState()
    val level by viewModel.level.collectAsState()
    val experience by viewModel.experience.collectAsState()

    Scaffold(
        topBar = {
            FinnSathiiTopBar(title = "Achievements")
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Level and XP Summary
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Level $level",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Total XP: ${viewModel.getTotalXP()}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LinearProgressIndicator(
                            progress = viewModel.getLevelProgress() / 100f,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${viewModel.getLevelProgress()}% to Level ${level + 1}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Achievements List
            items(achievements) { achievement ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (achievement.unlocked) 
                            MaterialTheme.colorScheme.secondaryContainer 
                        else 
                            MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Achievement Icon
                            if (achievement.unlocked) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Unlocked",
                                    tint = Color(0xFFFFD700)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Locked",
                                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            }

                            // Achievement Details
                            Column {
                                Text(
                                    text = achievement.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = if (achievement.unlocked) FontWeight.Bold else FontWeight.Normal
                                )
                                Text(
                                    text = achievement.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            }
                        }

                        // XP Points
                        Text(
                            text = "+${achievement.xpPoints} XP",
                            style = MaterialTheme.typography.labelLarge,
                            color = if (achievement.unlocked) 
                                MaterialTheme.colorScheme.primary 
                            else 
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}

data class Achievement(
    val title: String,
    val description: String,
    val unlocked: Boolean = false,
    val xpPoints: Int = 0
)
