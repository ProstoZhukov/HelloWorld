package com.zhukov.android.expensetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zhukov.android.myapplicationlist.R

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
            // For demo purposes we add a dummy expense
            ExpenseRepository.addExpense(Expense(10.0, "Demo", "Added manually"))
            adapter.notifyDataSetChanged()
        }
    }
}
