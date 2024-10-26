package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        enableEdgeToEdge()

//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets


        val firstName = findViewById<EditText>(R.id.etFirstName)
        val lastName = findViewById<EditText>(R.id.etLastName)
        val streetAddress = findViewById<EditText>(R.id.etStreetAddress)
        val city = findViewById<EditText>(R.id.etCity)

        val province = findViewById<EditText>(R.id.etProvince)
        val postalCode = findViewById<EditText>(R.id.etPostalCode)
        val email = findViewById<EditText>(R.id.etEmailAddress)
        val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber)

        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener{
            firstName.setText(" ")
            lastName.setText(" ")
            streetAddress.setText(" ")
            city.setText(" ")
            province.setText(" ")
            postalCode.setText(" ")
            email.setText(" ")
            phoneNumber.setText(" ")
            Toast.makeText(this, "Changes have been saved!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}