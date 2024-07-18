package com.example.dementiaapp.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("REMINDER_MESSAGE")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
