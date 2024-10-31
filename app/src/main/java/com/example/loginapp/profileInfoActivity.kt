package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class profileInfoActivity : AppCompatActivity() {

    lateinit var resultTextView: TextView
    lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileinfo)
        buttonEdit = findViewById(R.id.btnEdit)

        resultTextView = findViewById(R.id.tvResult)
        val sharedPref = getSharedPreferences("Profile Data", MODE_PRIVATE)
        val fname = sharedPref.getString("First Name", "")
        val lname = sharedPref.getString("Last Name", "")
        val strAdr = sharedPref.getString("Street Address", "")
        val city = sharedPref.getString("City", "")
        val prov = sharedPref.getString("Province", "")
        val postcode = sharedPref.getString("Postal Code", "")
        val email = sharedPref.getString("Email Address", "")
        val phonenum = sharedPref.getString("Phone Number", "")
        resultTextView.text = "First Name : $fname \nLast Name : $lname \nStreet Address : $strAdr \nCity : $city \nProvince : $prov \nPostal Code : $postcode \nEmail Address : $email \nPhone Number : $phonenum"

        buttonEdit.setOnClickListener{
            val mainActIntent = Intent(this, profileEditActivity::class.java)
            startActivity(mainActIntent)
        }

    }

}