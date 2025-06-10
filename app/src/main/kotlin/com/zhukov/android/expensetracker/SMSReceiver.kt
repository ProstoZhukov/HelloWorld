package com.zhukov.android.expensetracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import com.zhukov.android.expensetracker.ExpenseCategory

/**
 * BroadcastReceiver to listen for incoming SMS messages
 * and extract expense information.
 */
class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val bundle: Bundle? = intent.extras
            bundle?.let {
                val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                for (msg in messages) {
                    val body = msg.messageBody ?: continue
                    parseAndAddExpense(body)
                }
            }
        }
    }

    private fun parseAndAddExpense(body: String) {
        // Very naive parsing: look for a number in the text
        val regex = Regex("(\\d+([.,]\\d+)?)")
        val match = regex.find(body)
        val amount = match?.value?.replace(',', '.')?.toDoubleOrNull()
        if (amount != null) {
            val expense = Expense(amount, category = ExpenseCategory.SMS, description = body)
            ExpenseRepository.addExpense(expense)
            Log.d("SMSReceiver", "Added expense from SMS: $expense")
        }
    }
}
