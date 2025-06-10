package com.zhukov.android.expensetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhukov.android.myapplicationlist.R

class ExpenseAdapter(private val items: List<Expense>) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amount: TextView = view.findViewById(R.id.expense_amount)
        val category: TextView = view.findViewById(R.id.expense_category)
        val description: TextView = view.findViewById(R.id.expense_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.amount.text = item.amount.toString()
        holder.category.text = item.category.displayName()
        holder.description.text = item.description
    }
}
