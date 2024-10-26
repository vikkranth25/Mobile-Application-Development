package com.programminghut.realtime_object

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class screenBut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_but)

        var btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}