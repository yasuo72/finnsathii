package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.CategoryDao
import ro.si.finnsathii.data.mapper.toLocalModel
import ro.si.finnsathii.data.mapper.toRemoteModel
import ro.si.finnsathii.data.model.Category as RemoteCategory
import ro.si.finnsathii.data.model.enums.TransactionType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    fun getCategoriesByUser(userId: Long): Flow<List<RemoteCategory>> {
        return categoryDao.getCategoriesByUser(userId).map { localCategories ->
            localCategories.map { it.toRemoteModel() }
        }
    }

    suspend fun insertCategory(category: RemoteCategory): Long {
        return categoryDao.insertCategory(category.toLocalModel())
    }

    suspend fun updateCategory(category: RemoteCategory) {
        categoryDao.updateCategory(category.toLocalModel())
    }

    suspend fun deleteCategory(category: RemoteCategory) {
        categoryDao.deleteCategory(category.toLocalModel())
    }

    fun getCategoryById(categoryId: Long): Flow<RemoteCategory?> {
        return categoryDao.getCategoryById(categoryId).map { it?.toRemoteModel() }
    }

    fun getCategoriesByType(userId: Long, type: TransactionType): Flow<List<RemoteCategory>> {
        return categoryDao.getCategoriesByType(userId, type).map { localCategories ->
            localCategories.map { it.toRemoteModel() }
        }
    }
}
