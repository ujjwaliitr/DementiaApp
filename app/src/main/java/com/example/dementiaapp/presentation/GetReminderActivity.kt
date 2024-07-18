package com.example.dementiaapp.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.dementiaapp.R

class GetReminderActivity : Activity() {

    private lateinit var remindersList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_reminder)

        remindersList = findViewById(R.id.reminders_list)

        val reminders = getReminders()
        remindersList.text = reminders
    }

    private fun getReminders(): String {
        return "No reminders set."
    }
}
