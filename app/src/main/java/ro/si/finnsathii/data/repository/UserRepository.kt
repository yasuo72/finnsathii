package ro.si.finnsathii.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.si.finnsathii.data.local.dao.UserDao
import ro.si.finnsathii.data.mapper.toLocalModel
import ro.si.finnsathii.data.mapper.toRemoteModel
import ro.si.finnsathii.data.model.User as RemoteUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getUserById(userId: String): Flow<RemoteUser?> {
        return userDao.getUserById(userId).map { it?.toRemoteModel() }
    }

    fun getUserByEmail(email: String): Flow<RemoteUser?> {
        return userDao.getUserByEmail(email).map { it?.toRemoteModel() }
    }

    suspend fun insertUser(user: RemoteUser): Long {
        return userDao.insertUser(user.toLocalModel())
    }

    suspend fun updateUser(user: RemoteUser) {
        userDao.updateUser(user.toLocalModel())
    }

    suspend fun deleteUser(user: RemoteUser) {
        userDao.deleteUser(user.toLocalModel())
    }

    suspend fun updateLastLoginTimestamp(userId: String, loginTimestamp: Long) {
        userDao.updateLastLoginTimestamp(userId, loginTimestamp)
    }

    suspend fun updateEmailVerificationStatus(userId: String, verified: Boolean) {
        userDao.updateEmailVerificationStatus(userId, verified)
    }
}
