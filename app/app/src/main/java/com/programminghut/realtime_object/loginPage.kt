package com.programminghut.realtime_object

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class loginPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Get references to UI elements
        val emailLog = findViewById<TextInputEditText>(R.id.emailLog)
        val passLog = findViewById<TextInputEditText>(R.id.passLog)
        val buttonLogin = findViewById<Button>(R.id.button1)


        // Register button functionality


        // Login button functionality
        buttonLogin.setOnClickListener {
            val email = emailLog.text.toString()
            val password = passLog.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                            intent = Intent(applicationContext, screenBut::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Optional: Data model for User
    data class User(val email: String, val password: String)
}