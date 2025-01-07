package ro.si.finnsathii.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ro.si.finnsathii.data.local.dao.*
import ro.si.finnsathii.data.local.model.*
import ro.si.finnsathii.data.util.Converters

@Database(
    entities = [
        Transaction::class,
        Category::class,
        Budget::class,
        Goal::class,
        User::class,
        Achievement::class,
        UserProgress::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetDao(): BudgetDao
    abstract fun goalDao(): GoalDao
    abstract fun userDao(): UserDao
    abstract fun achievementDao(): AchievementDao
    abstract fun userProgressDao(): UserProgressDao
}
