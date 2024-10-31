package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

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
        //All the info shown in textview box
        resultTextView.text = "First Name : $fname \nLast Name : $lname \nStreet Address : $strAdr \nCity : $city \nProvince : $prov \nPostal Code : $postcode \nEmail Address : $email \nPhone Number : $phonenum"

        buttonEdit.setOnClickListener{
            val mainActIntent = Intent(this, profileEditActivity::class.java)
            startActivity(mainActIntent)
        }

        // Initialize and assign variable
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set profile selected
        bottomNavigationView.selectedItemId = R.id.profile

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.wishlist -> {
                    startActivity(Intent(applicationContext, SecondActivity::class.java))
                    // Deprecated function to remove transition but it works for now, a fix would be to comment it out but it will show a transition
                    // Comes from converting java code to kotlin
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity2::class.java))
                    // Deprecated function to remove transition but it works for now, a fix would be to comment it out but it will show a transition
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.profile -> true
                else -> false
            }
        }

    }

}