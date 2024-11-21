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
import com.example.loginapp.Database.LoginInfoDatabaseHelper


class LoginActivity : AppCompatActivity() {
    // makes it so that we can access username in profileEdit so we can make changes to db
    companion object{
        var username = ""
    }

    private lateinit var usernameInput :EditText
    private lateinit var passwordInput : EditText
    private lateinit var loginBtn :Button
    private lateinit var signUpLink : TextView
    private lateinit var db : LoginInfoDatabaseHelper

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
        db = LoginInfoDatabaseHelper(this)

        loginBtn.setOnClickListener {
            username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // checks for valid input
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                if (db.checkLoginInfo(username, password)) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
        }

        signUpLink.setOnClickListener{
            val intent = Intent(this,signUpActivity::class.java)
            startActivity(intent)
        }
    }
}