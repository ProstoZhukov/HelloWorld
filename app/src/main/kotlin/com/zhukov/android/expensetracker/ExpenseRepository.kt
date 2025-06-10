package com.zhukov.android.expensetracker

/**
 * Simple in-memory repository for expenses.
 * In a real application this would persist data in a database.
 */
object ExpenseRepository {
    private val expenses = mutableListOf<Expense>()

    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun getExpenses(): List<Expense> = expenses.toList()
}
