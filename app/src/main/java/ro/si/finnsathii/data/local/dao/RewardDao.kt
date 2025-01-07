package ro.si.finnsathii.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ro.si.finnsathii.data.model.Reward

@Dao
interface RewardDao {
    @Query("SELECT * FROM rewards WHERE userId = :userId")
    fun getAllRewards(userId: Long): Flow<List<Reward>>

    @Query("SELECT * FROM rewards WHERE userId = :userId AND isRedeemed = 0")
    fun getUnredeemedRewards(userId: Long): Flow<List<Reward>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReward(reward: Reward)

    @Update
    suspend fun updateReward(reward: Reward)

    @Query("UPDATE rewards SET isRedeemed = 1 WHERE id = :rewardId")
    suspend fun redeemReward(rewardId: Long)

    @Query("SELECT COUNT(*) FROM rewards WHERE userId = :userId AND isRedeemed = 0")
    fun getUnredeemedRewardCount(userId: Long): Flow<Int>
}
