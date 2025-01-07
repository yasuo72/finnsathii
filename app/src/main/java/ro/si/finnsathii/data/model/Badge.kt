package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "badges")
data class Badge(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val iconUrl: String,
    val requiredPoints: Int,
    val category: BadgeCategory,
    val dateEarned: Date? = null,
    val userId: String
)

enum class BadgeCategory {
    SAVINGS,
    BUDGETING,
    INVESTMENT,
    STREAK,
    SPECIAL
}
