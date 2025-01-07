package ro.si.finnsathii.data.local

import androidx.room.TypeConverter
import ro.si.finnsathii.data.model.enums.*
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromRecurringPeriod(value: RecurringPeriod?): String? {
        return value?.name
    }

    @TypeConverter
    fun toRecurringPeriod(value: String?): RecurringPeriod? {
        return value?.let { RecurringPeriod.valueOf(it) }
    }

    @TypeConverter
    fun fromGoalType(value: GoalType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toGoalType(value: String?): GoalType? {
        return value?.let { GoalType.valueOf(it) }
    }

    @TypeConverter
    fun fromStrategyType(value: StrategyType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toStrategyType(value: String?): StrategyType? {
        return value?.let { StrategyType.valueOf(it) }
    }

    @TypeConverter
    fun fromStrategyDifficulty(value: StrategyDifficulty?): String? {
        return value?.name
    }

    @TypeConverter
    fun toStrategyDifficulty(value: String?): StrategyDifficulty? {
        return value?.let { StrategyDifficulty.valueOf(it) }
    }
}
