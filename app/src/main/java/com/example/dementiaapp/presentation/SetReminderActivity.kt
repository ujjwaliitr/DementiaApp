package com.example.dementiaapp.presentation

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
        val time = reminderTime.text.toString()
        val message = reminderMessage.text.toString()

        val timeParts = time.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val intent = Intent(this, ReminderReceiver::class.java)
        intent.putExtra("REMINDER_MESSAGE", message)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show()
    }
}
