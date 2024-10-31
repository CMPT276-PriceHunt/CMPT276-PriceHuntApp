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
    lateinit var editTextStreetAddress: EditText
    lateinit var editTextCity: EditText
    lateinit var editTextProvince: EditText
    lateinit var editTextPostalCode: EditText
    lateinit var editTextEmailAddress: EditText
    lateinit var editTextPhoneNumber: EditText
    lateinit var buttonSave: Button
    lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileedit)
        editTextFirstName = findViewById(R.id.etFirstName)
        editTextLastName = findViewById(R.id.etLastName)
        editTextStreetAddress = findViewById(R.id.etStreetAddress)
        editTextCity = findViewById(R.id.etCity)
        editTextProvince = findViewById(R.id.etProvince)
        editTextPostalCode = findViewById(R.id.etPostalCode)
        editTextEmailAddress = findViewById(R.id.etEmailAddress)
        editTextPhoneNumber = findViewById(R.id.etPhoneNumber)
        buttonSave = findViewById(R.id.btnSave)
        buttonBack = findViewById(R.id.btnBack)

        buttonSave.setOnClickListener {
            val sharedPref = getSharedPreferences("Profile Data", MODE_PRIVATE)

            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()
            val city = editTextCity.text.toString()
            val streetAdr = editTextStreetAddress.text.toString()
            val province = editTextProvince.text.toString()
            val postalCode = editTextPostalCode.text.toString()
            val email = editTextEmailAddress.text.toString()
            val phoneNum = editTextPhoneNumber.text.toString()

            val editor = sharedPref.edit()
            editor.putString("First Name", firstName)
            editor.putString("Last Name", lastName)
            editor.putString("City", city)
            editor.putString("Street Address", streetAdr)
            editor.putString("Province", province)
            editor.putString("Postal Code", postalCode)
            editor.putString("Email Address", email)
            editor.putString("Phone Number", phoneNum)
            editor.apply()

            Toast.makeText(this, "Changes have been saved!", Toast.LENGTH_SHORT).show()
            val saveBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(saveBtnIntent)
        }

        buttonBack.setOnClickListener{
            val backBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(backBtnIntent)
        }
    }
}