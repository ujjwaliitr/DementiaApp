package com.example.dementiaapp.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.dementiaapp.R

class GetReminderActivity : Activity() {

    private lateinit var remindersListView: ListView
    private lateinit var adapter: ReminderAdapter
    private lateinit var reminders: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_reminder)

        remindersListView = findViewById(R.id.reminders_list)
        reminders = getReminders().toMutableList()
        adapter = ReminderAdapter(this, reminders) { position ->
            deleteReminder(position)
        }
        remindersListView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadReminders()
    }

    private fun loadReminders() {
        reminders.clear()
        reminders.addAll(getReminders())
        adapter.notifyDataSetChanged()
    }

    private fun getReminders(): List<String> {
        val sharedPreferences = getSharedPreferences("reminders", MODE_PRIVATE)
        val remindersSet = sharedPreferences.getStringSet("reminders_set", emptySet())!!
        return remindersSet.toList()
    }

    private fun deleteReminder(position: Int) {
        val sharedPreferences = getSharedPreferences("reminders", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val remindersSet = sharedPreferences.getStringSet("reminders_set", mutableSetOf())!!.toMutableSet()
        remindersSet.remove(reminders[position])
        editor.putStringSet("reminders_set", remindersSet)
        editor.apply()

        reminders.removeAt(position)
        adapter.notifyDataSetChanged()

        Toast.makeText(this, "Reminder Deleted", Toast.LENGTH_SHORT).show()
    }
}
