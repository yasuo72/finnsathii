package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.Category
import ro.si.finnsathii.data.model.enums.TransactionType

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE userId = :userId")
    fun getCategoriesByUser(userId: Long): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE userId = :userId AND transactionType = :transactionType")
    fun getCategoriesByType(userId: Long, transactionType: TransactionType): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategoryById(categoryId: Long): Flow<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}
