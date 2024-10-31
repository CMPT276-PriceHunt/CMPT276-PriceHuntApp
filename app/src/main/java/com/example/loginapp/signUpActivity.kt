package com.example.loginapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class signUpActivity : AppCompatActivity() {
    private lateinit var backBtn : ImageButton
    private lateinit var usernameSignup :EditText
    private lateinit var passwordSignup : EditText
    private lateinit var signupBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        enableEdgeToEdge()

        backBtn = findViewById(R.id.backArrow)
        usernameSignup = findViewById(R.id.username_signup)
        passwordSignup = findViewById(R.id.password_signup)
        signupBtn = findViewById(R.id.button_id_signup)

        // this is the back arrow button that takes you back to login page
        backBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // sign up button
        signupBtn.setOnClickListener{
            val username = usernameSignup.text.toString()
            val password = passwordSignup.text.toString()

            // functions to save our data
            var sharedPref = getSharedPreferences("Login Data", MODE_PRIVATE)
            val editor =sharedPref.edit()

            editor.putString("Username", username)
            editor.putString("Password", password)
            editor.apply()

            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_LONG).show()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}