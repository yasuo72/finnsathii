package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "rewards")
data class Reward(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val type: RewardType,
    val value: Int,
    val dateEarned: Date? = null,
    val expiryDate: Date? = null,
    val userId: String,
    val isRedeemed: Boolean = false
)

enum class RewardType {
    XP_BOOST,         // Temporarily increases XP earned
    STREAK_SHIELD,    // Protects streak from breaking once
    BADGE,            // Cosmetic reward
    THEME_UNLOCK,     // Unlocks special app theme
    MILESTONE         // Special achievement milestone
}
