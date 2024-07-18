package com.example.dementiaapp.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import com.example.dementiaapp.R

class MainActivity : Activity() {

    private lateinit var callButton: Button
    private lateinit var setReminderButton: Button
    private lateinit var getReminderButton: Button
    private lateinit var aboutMeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callButton = findViewById(R.id.call_button)
        setReminderButton = findViewById(R.id.set_reminder_button)
        getReminderButton = findViewById(R.id.get_reminder_button)
        aboutMeButton = findViewById(R.id.about_me_button)

        callButton.setOnClickListener {
            callCaregiver()
        }

        setReminderButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, SetReminderActivity::class.java))
        }

        getReminderButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, GetReminderActivity::class.java))
        }

        aboutMeButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AboutMeActivity::class.java))
        }
    }

    private fun callCaregiver() {
        val primaryCaregiverNumber = "tel:1234567890"
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse(primaryCaregiverNumber)
        startActivity(callIntent)
    }
}
