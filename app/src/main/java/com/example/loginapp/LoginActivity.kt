package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput :EditText
    private lateinit var passwordInput : EditText
    private lateinit var loginBtn :Button
    private lateinit var signUpLink : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)
        signUpLink = findViewById(R.id.signUpLink)

        loginBtn.setOnClickListener{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // default value are admin and lambda123
            // these functions check if the username and password are correct
            val sharedPref = getSharedPreferences("Login Data", MODE_PRIVATE)
            val user = sharedPref.getString("Username", "admin")
            val pass = sharedPref.getString("Password", "lambda123")

            if (username != user || password != pass) {
                Toast.makeText(this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }

        signUpLink.setOnClickListener{
            val intent = Intent(this,signUpActivity::class.java)
            startActivity(intent)
        }
    }
}