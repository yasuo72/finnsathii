package ro.si.finnsathii.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val email: String,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val registrationDate: Long = System.currentTimeMillis(),
    val lastLoginDate: Long = System.currentTimeMillis(),
    val isEmailVerified: Boolean = false
)
