package com.example.dementiaapp.presentation

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dementiaapp.R
import java.util.*

class SetReminderActivity : Activity() {

    private lateinit var reminderTime: EditText
    private lateinit var reminderMessage: EditText
    private lateinit var setReminderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_reminder)

        reminderTime = findViewById(R.id.reminder_time)
        reminderMessage = findViewById(R.id.reminder_message)
        setReminderButton = findViewById(R.id.set_reminder_button)

        setReminderButton.setOnClickListener {
            setReminder()
        }
    }

    private fun setReminder() {
        try {
            val time = reminderTime.text.toString()
            val message = reminderMessage.text.toString()

            if (!isValidTimeFormat(time)) {
                Toast.makeText(this, "Invalid time format. Use HH:mm.", Toast.LENGTH_SHORT).show()
                return
            }

            val timeParts = time.split(":")
            val hour = timeParts[0].toInt()
            val minute = timeParts[1].toInt()

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            saveReminder(time, message)
            setAlarm(calendar.timeInMillis, message)
            Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SetReminderActivity", "Failed to set reminder", e)
            Toast.makeText(this, "Failed to set reminder", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidTimeFormat(time: String): Boolean {
        val regex = Regex("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
        return time.matches(regex)
    }

    private fun saveReminder(time: String, message: String) {
        try {
            val sharedPreferences = getSharedPreferences("reminders", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val reminders = sharedPreferences.getStringSet("reminders_set", mutableSetOf())!!.toMutableSet()
            reminders.add("$time - $message")
            editor.putStringSet("reminders_set", reminders)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SetReminderActivity", "Failed to save reminder", e)
            throw e
        }
    }

    private fun setAlarm(timeInMillis: Long, message: String) {
        try {
            val intent = Intent(this, ReminderReceiver::class.java)
            intent.putExtra("REMINDER_MESSAGE", message)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SetReminderActivity", "Failed to set alarm", e)
            throw e
        }
    }
}
