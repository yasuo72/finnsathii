package ro.si.finnsathii.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.local.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<User>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("UPDATE users SET lastLoginDate = :loginTimestamp WHERE id = :userId")
    suspend fun updateLastLoginTimestamp(userId: String, loginTimestamp: Long)

    @Query("UPDATE users SET isEmailVerified = :verified WHERE id = :userId")
    suspend fun updateEmailVerificationStatus(userId: String, verified: Boolean)
}
