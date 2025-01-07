package ro.si.finnsathii.data.mapper

import ro.si.finnsathii.data.local.model.User as LocalUser
import ro.si.finnsathii.data.local.model.Budget as LocalBudget
import ro.si.finnsathii.data.local.model.Category as LocalCategory
import ro.si.finnsathii.data.local.model.Goal as LocalGoal
import ro.si.finnsathii.data.local.model.Transaction as LocalTransaction

import ro.si.finnsathii.data.model.User as RemoteUser
import ro.si.finnsathii.data.model.Budget as RemoteBudget
import ro.si.finnsathii.data.model.Category as RemoteCategory
import ro.si.finnsathii.data.model.Goal as RemoteGoal
import ro.si.finnsathii.data.model.Transaction as RemoteTransaction
import ro.si.finnsathii.data.model.enums.TransactionType
import ro.si.finnsathii.data.model.enums.GoalCategory
import ro.si.finnsathii.data.model.enums.GoalStatus
import ro.si.finnsathii.data.model.enums.GoalTimeframe
import java.util.Date

// User Mapping
fun RemoteUser.toLocalModel() = LocalUser(
    id = this.id,
    email = this.email,
    displayName = this.displayName ?: "",
    photoUrl = this.photoUrl ?: ""
)

fun LocalUser.toRemoteModel() = RemoteUser(
    id = this.id,
    email = this.email,
    displayName = this.displayName,
    photoUrl = this.photoUrl
)

// Budget Mapping
fun RemoteBudget.toLocalModel() = LocalBudget(
    id = this.id,
    userId = this.userId,
    category = this.category,
    amount = this.amount,
    spent = this.spent,
    startDate = this.startDate,
    endDate = this.endDate,
    recurringPeriod = this.recurringPeriod,
    progress = this.progress
)

fun LocalBudget.toRemoteModel() = RemoteBudget(
    id = this.id,
    userId = this.userId,
    category = this.category,
    amount = this.amount,
    spent = this.spent,
    startDate = this.startDate,
    endDate = this.endDate,
    recurringPeriod = this.recurringPeriod,
    progress = this.progress
)

// Category Mapping
fun RemoteCategory.toLocalModel() = LocalCategory(
    id = this.id,
    userId = this.userId,
    name = this.name,
    transactionType = this.transactionType,
    icon = this.icon,
    color = this.color,
    isDefault = this.isDefault
)

fun LocalCategory.toRemoteModel() = RemoteCategory(
    id = this.id,
    userId = this.userId,
    name = this.name,
    transactionType = this.transactionType,
    icon = this.icon,
    color = this.color,
    isDefault = this.isDefault
)

// Goal Mapping
fun RemoteGoal.toLocalModel() = LocalGoal(
    id = this.id,
    userId = this.userId,
    title = this.title,
    description = this.description,
    targetAmount = this.targetAmount,
    currentAmount = this.currentAmount,
    startDate = this.startDate,
    endDate = this.endDate,
    category = this.category,
    timeframe = this.timeframe,
    status = this.status,
    lastUpdated = this.lastUpdated
)

fun LocalGoal.toRemoteModel() = RemoteGoal(
    id = this.id,
    userId = this.userId,
    title = this.title,
    description = this.description,
    targetAmount = this.targetAmount,
    currentAmount = this.currentAmount,
    startDate = this.startDate,
    endDate = this.endDate,
    category = this.category,
    timeframe = this.timeframe,
    status = this.status,
    lastUpdated = this.lastUpdated
)

// Transaction Mapping
fun RemoteTransaction.toLocalModel() = LocalTransaction(
    id = this.id,
    userId = this.userId,
    amount = this.amount,
    type = this.type,
    categoryId = this.categoryId,
    description = this.description,
    date = this.date
)

fun LocalTransaction.toRemoteModel() = RemoteTransaction(
    id = this.id,
    userId = this.userId,
    amount = this.amount,
    description = this.description,
    date = this.date,
    type = this.type,
    categoryId = this.categoryId,
    isRecurring = false
)
