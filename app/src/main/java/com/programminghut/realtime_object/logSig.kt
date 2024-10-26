package com.programminghut.realtime_object

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class logSig : AppCompatActivity() {
    // Declare Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_sig)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Get references to UI elements
        val emailLog = findViewById<TextInputEditText>(R.id.emailLog)
        val passLog = findViewById<TextInputEditText>(R.id.passLog)
        val emailReg = findViewById<TextInputEditText>(R.id.emailReg)
        val passReg = findViewById<TextInputEditText>(R.id.passReg)
        val buttonLogin = findViewById<Button>(R.id.button1)
        val buttonRegister = findViewById<Button>(R.id.button2)

        // Register button functionality
        buttonRegister.setOnClickListener {
            val email = emailReg.text.toString()
            val password = passReg.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Get user ID
                            val userId = auth.currentUser?.uid

                            // Push to Firebase Realtime Database under "users"
                            val database = FirebaseDatabase.getInstance().getReference("users")
                            userId?.let {
                                val user = User(email, password) // Create User data model
                                database.child(userId).setValue(user)
                            }

                            // Show success message
                            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }

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