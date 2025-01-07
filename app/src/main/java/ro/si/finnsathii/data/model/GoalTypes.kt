package ro.si.finnsathii.data.model

enum class GoalType {
    SAVINGS, DEBT_PAYMENT, INVESTMENT, EMERGENCY_FUND, RETIREMENT, EDUCATION, OTHER
}

enum class StrategyType {
    AUTOMATIC_DEDUCTION, EXPENSE_REDUCTION, INCOME_INCREASE, DEBT_CONSOLIDATION, INVESTMENT_STRATEGY
}

enum class StrategyDifficulty {
    EASY, MODERATE, CHALLENGING
}

data class SavingStrategy(
    val name: String,
    val description: String,
    val type: StrategyType,
    val suggestedDuration: Int, // in months
    val difficulty: StrategyDifficulty,
    val potentialSavings: Double,
    val isAutomated: Boolean = false
)
