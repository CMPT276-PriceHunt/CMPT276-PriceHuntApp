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

    lateinit var firstname: TextView
    lateinit var lastname: TextView
    lateinit var email: TextView
    lateinit var phonenum: TextView
    lateinit var city: TextView
    lateinit var prov: TextView
    lateinit var address: TextView
    lateinit var postcode: TextView
    lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileinfo)
        buttonEdit = findViewById(R.id.btnEdit)

        firstname = findViewById<TextView>(R.id.fname)
        lastname = findViewById<TextView>(R.id.lname)
        email = findViewById<TextView>(R.id.email)
        phonenum = findViewById<TextView>(R.id.phone)
        city = findViewById<TextView>(R.id.city)
        prov = findViewById<TextView>(R.id.prov)
        address = findViewById<TextView>(R.id.address)
        postcode = findViewById<TextView>(R.id.postcode)


        val sharedPref = getSharedPreferences("Profile Data", MODE_PRIVATE)
        val fname = sharedPref.getString("First Name", "")
        val lname = sharedPref.getString("Last Name", "")
        val address = sharedPref.getString("Street Address", "")
        val city = sharedPref.getString("City", "")
        val prov = sharedPref.getString("Province", "")
        val postcode = sharedPref.getString("Postal Code", "")
        val email = sharedPref.getString("Email Address", "")
        val phonenum = sharedPref.getString("Phone Number", "")
        //All the info shown in textview box
        //resultTextView.text = "First Name : $fname \nLast Name : $lname \nStreet Address : $address \nCity : $city \nProvince : $prov \nPostal Code : $postcode \nEmail Address : $email \nPhone Number : $phonenum"

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
                    startActivity(Intent(applicationContext, HomeWishlist::class.java))
                    // Deprecated function to remove transition but it works for now, a fix would be to comment it out but it will show a transition
                    // Comes from converting java code to kotlin
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.home -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
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