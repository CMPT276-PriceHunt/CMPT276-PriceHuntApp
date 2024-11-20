package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class profileEditActivity : AppCompatActivity() {

    lateinit var editTextFirstName: EditText
    lateinit var editTextLastName: EditText
    lateinit var editTextAddress: EditText
    lateinit var editTextCity: EditText
    lateinit var editTextProvince: EditText
    lateinit var editTextPostalCode: EditText
    lateinit var editTextEmailAddress: EditText
    lateinit var editTextPhoneNumber: EditText
    lateinit var buttonSave: Button
    lateinit var buttonBack: Button
    lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileedit)
        editTextFirstName = findViewById(R.id.etFirstName)
        editTextLastName = findViewById(R.id.etLastName)
        editTextAddress = findViewById(R.id.etStreetAddress)
        editTextCity = findViewById(R.id.etCity)
        editTextProvince = findViewById(R.id.etProvince)
        editTextPostalCode = findViewById(R.id.etPostalCode)
        editTextEmailAddress = findViewById(R.id.etEmailAddress)
        editTextPhoneNumber = findViewById(R.id.etPhoneNumber)
        buttonSave = findViewById(R.id.btnSave)
        buttonBack = findViewById(R.id.btnBack)
        buttonClear = findViewById(R.id.btnClear)

        val sharedPref = getSharedPreferences("Profile Data", MODE_PRIVATE)

        // Retrieve and set text
        editTextFirstName.setText(sharedPref.getString("First Name", ""))
        editTextLastName.setText(sharedPref.getString("Last Name", ""))
        editTextAddress.setText(sharedPref.getString("Street Address", ""))
        editTextCity.setText(sharedPref.getString("City", ""))
        editTextProvince.setText(sharedPref.getString("Province", ""))
        editTextPostalCode.setText(sharedPref.getString("Postal Code", ""))
        editTextEmailAddress.setText(sharedPref.getString("Email Address", ""))
        editTextPhoneNumber.setText(sharedPref.getString("Phone Number", ""))

        buttonSave.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putString("First Name", editTextFirstName.text.toString())
            editor.putString("Last Name", editTextLastName.text.toString())
            editor.putString("City", editTextCity.text.toString())
            editor.putString("Street Address", editTextAddress.text.toString())
            editor.putString("Province", editTextProvince.text.toString())
            editor.putString("Postal Code", editTextPostalCode.text.toString())
            editor.putString("Email Address", editTextEmailAddress.text.toString())
            editor.putString("Phone Number", editTextPhoneNumber.text.toString())

            if (editTextFirstName.text.isEmpty() || editTextLastName.text.isEmpty() || editTextCity.text.isEmpty() || editTextAddress.text.isEmpty() || editTextProvince.text.isEmpty() || editTextPostalCode.text.isEmpty() || editTextEmailAddress.text.isEmpty() || editTextPhoneNumber.text.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (editTextPhoneNumber.text.length != 10){
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (editTextPostalCode.text.length != 6){
                Toast.makeText(this, "Please enter a valid postal code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (editTextProvince.text.length != 2){
                Toast.makeText(this, "Please enter a valid province", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            editor.apply()

            Toast.makeText(this, "Changes have been saved!", Toast.LENGTH_SHORT).show()
            val saveBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(saveBtnIntent)
        }

        buttonBack.setOnClickListener{
            val backBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(backBtnIntent)
        }

        buttonClear.setOnClickListener{
            val sharedPref = getSharedPreferences("Profile Data", MODE_PRIVATE) //database initialization
            val editor = sharedPref.edit() //variable to manipulate data from the database

            editor.putString("First Name", "")
            editor.putString("Last Name", "")
            editor.putString("City", "")
            editor.putString("Street Address", "")
            editor.putString("Province", "")
            editor.putString("Postal Code", "")
            editor.putString("Email Address", "")
            editor.putString("Phone Number", "")
            editor.apply()

            Toast.makeText(this, "All fields have been cleared!", Toast.LENGTH_SHORT).show()
            val clearBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(clearBtnIntent)
        }
    }
}