package com.example.dementiaapp.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            val primaryCaregiverNumber = "tel:1234567890"
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse(primaryCaregiverNumber)
            startActivity(callIntent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    callCaregiver()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}
