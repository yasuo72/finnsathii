package ro.si.finnsathii.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    
    @ColumnInfo(name = "email")
    val email: String = "",
    
    @ColumnInfo(name = "displayName")
    val displayName: String? = null,
    
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String? = null,
    
    @ColumnInfo(name = "registrationDate")
    val registrationDate: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "lastLoginDate")
    val lastLoginDate: Long = System.currentTimeMillis(),
    
    @ColumnInfo(name = "isEmailVerified")
    val isEmailVerified: Boolean = false
) {
    companion object {
        fun fromFirebaseUser(uid: String, email: String?, displayName: String?, isEmailVerified: Boolean): User {
            return User(
                id = uid,
                email = email ?: "",
                displayName = displayName,
                isEmailVerified = isEmailVerified
            )
        }
    }
}
