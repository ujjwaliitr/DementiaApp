package com.example.dementiaapp.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.dementiaapp.R

class AboutMeActivity : Activity() {

    private lateinit var aboutMeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        aboutMeText = findViewById(R.id.about_me_text)
        aboutMeText.text = "Hi I am John.I sometime get lost.Please contact my son Paul:+44123456789,125-Church Street London"
    }
}
