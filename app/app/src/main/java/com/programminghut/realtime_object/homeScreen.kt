package com.programminghut.realtime_object

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class homeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val btnlog = findViewById<Button>(R.id.button3)
        val btnsig = findViewById<Button>(R.id.button4)

        btnlog.setOnClickListener{
            intent = Intent(applicationContext, loginPage::class.java)
            startActivity(intent)
        }

        btnsig.setOnClickListener{
            intent = Intent(applicationContext, registerPage::class.java)
            startActivity(intent)
        }
    }
}