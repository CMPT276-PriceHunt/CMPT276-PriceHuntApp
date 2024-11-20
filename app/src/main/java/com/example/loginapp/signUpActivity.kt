package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

// importing our database
import com.example.loginapp.Database.LoginInfoDatabaseHelper
import com.example.loginapp.Database.loginInfo


class signUpActivity : AppCompatActivity() {
    private lateinit var backBtn : ImageButton
    private lateinit var usernameSignup :EditText
    private lateinit var passwordSignup : EditText
    private lateinit var signupBtn : Button
    private lateinit var db : LoginInfoDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        enableEdgeToEdge()

        backBtn = findViewById(R.id.backArrow)
        usernameSignup = findViewById(R.id.username_signup)
        passwordSignup = findViewById(R.id.password_signup)
        signupBtn = findViewById(R.id.button_id_signup)
        db = LoginInfoDatabaseHelper(this)

        // this is the back arrow button that takes you back to login page
        backBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // sign up button
        signupBtn.setOnClickListener{
            val username = usernameSignup.text.toString()
            val password = passwordSignup.text.toString()

            // cannot save nothing as username and password
            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // functions to save our data
            val loginInfo = loginInfo(username, password)
            db.insertLoginInfo(loginInfo)
            finish()

            // this assumes that something was inputted for signup
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_LONG).show()

            // back to login screen
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}