package ro.si.finnsathii.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.model.GoalSuggestion
import ro.si.finnsathii.data.model.enums.GoalCategory

@Dao
interface GoalSuggestionDao {
    @Query("SELECT * FROM goal_suggestions")
    fun getAllGoalSuggestions(): Flow<List<GoalSuggestion>>

    @Query("SELECT * FROM goal_suggestions WHERE category = :category")
    fun getGoalSuggestionsByCategory(category: GoalCategory): Flow<List<GoalSuggestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoalSuggestion(goalSuggestion: GoalSuggestion)

    @Update
    suspend fun updateGoalSuggestion(goalSuggestion: GoalSuggestion)

    @Delete
    suspend fun deleteGoalSuggestion(goalSuggestion: GoalSuggestion)

    @Query("SELECT * FROM goal_suggestions WHERE id = :suggestionId")
    fun getGoalSuggestionById(suggestionId: Long): Flow<GoalSuggestion?>
}
