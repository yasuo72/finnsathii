package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.Achievement
import ro.si.finnsathii.data.model.enums.AchievementCategory
import java.util.Date

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements WHERE userId = :userId AND category = :category")
    fun getAchievementsByCategory(userId: Long, category: AchievementCategory): Flow<List<Achievement>>

    @Query("SELECT * FROM achievements WHERE userId = :userId AND category = :category LIMIT 1")
    fun getAchievementByCategory(userId: Long, category: AchievementCategory): Flow<Achievement?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: Achievement)

    @Update
    suspend fun updateAchievement(achievement: Achievement)

    @Query("UPDATE achievements SET progress = :newProgress, dateCompleted = :completedDate WHERE id = :achievementId")
    suspend fun updateAchievementProgress(achievementId: Long, newProgress: Int, completedDate: Date?)
}
