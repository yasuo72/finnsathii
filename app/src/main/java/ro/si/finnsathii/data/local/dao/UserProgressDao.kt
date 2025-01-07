package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.UserProgress

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE userId = :userId")
    fun getUserProgress(userId: Long): Flow<UserProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProgress(userProgress: UserProgress)

    @Update
    suspend fun updateUserProgress(userProgress: UserProgress)

    @Query("UPDATE user_progress SET streakDays = streakDays + 1 WHERE userId = :userId")
    suspend fun incrementStreak(userId: Long)

    @Query("UPDATE user_progress SET currentXp = currentXp + :xpAmount WHERE userId = :userId")
    suspend fun addXp(userId: Long, xpAmount: Long)
}
