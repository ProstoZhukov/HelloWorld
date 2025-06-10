package com.zhukov.android.expensetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zhukov.android.myapplicationlist.R

// Import the predefined categories
import com.zhukov.android.expensetracker.ExpenseCategory
import com.zhukov.android.expensetracker.AddExpenseDialogFragment

/**
 * Main activity showing the list of expenses.
 */
class ExpenseTrackerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_tracker)

        recyclerView = findViewById(R.id.expense_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(ExpenseRepository.getExpenses())
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.add_expense_button).setOnClickListener {
            AddExpenseDialogFragment {
                adapter.notifyDataSetChanged()
            }.show(supportFragmentManager, "addExpense")
        }
    }
}
