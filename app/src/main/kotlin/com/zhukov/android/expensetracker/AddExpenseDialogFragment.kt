package com.zhukov.android.expensetracker

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zhukov.android.myapplicationlist.R

/**
 * Simple dialog to create a new expense entry.
 */
class AddExpenseDialogFragment(private val onAdded: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_expense, null)
        val amountField = view.findViewById<EditText>(R.id.new_expense_amount)
        val categoryField = view.findViewById<android.widget.Spinner>(R.id.new_expense_category)
        val descField = view.findViewById<EditText>(R.id.new_expense_description)

        amountField.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        val categories = ExpenseCategory.values().map { it.displayName() }
        categoryField.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_expense_title)
            .setView(view)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val amount = amountField.text.toString().toDoubleOrNull() ?: return@setPositiveButton
                val catIndex = categoryField.selectedItemPosition
                val category = ExpenseCategory.values()[catIndex]
                val desc = descField.text.toString()
                ExpenseRepository.addExpense(Expense(amount, category, desc))
                onAdded()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }
}
