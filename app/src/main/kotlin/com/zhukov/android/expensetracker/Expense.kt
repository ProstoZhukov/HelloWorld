package com.zhukov.android.expensetracker

import java.util.Date

/**
 * Data class representing a single expense entry.
 */
data class Expense(
    val amount: Double,
    val category: String,
    val description: String = "",
    val date: Date = Date()
)
