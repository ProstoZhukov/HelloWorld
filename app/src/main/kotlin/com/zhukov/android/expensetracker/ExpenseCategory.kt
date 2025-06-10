package com.zhukov.android.expensetracker

/**
 * Preset categories for expenses.
 * This can be extended or loaded from a database in a real app.
 */
enum class ExpenseCategory {
    FOOD,
    TRANSPORT,
    HOUSING,
    UTILITIES,
    ENTERTAINMENT,
    COMMUNICATION,
    SHOPPING,
    HEALTH,
    EDUCATION,
    TRAVEL,
    TAXES,
    INSURANCE,
    SAVINGS,
    GIFTS,
    SMS,
    OTHER;

    fun displayName(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}
