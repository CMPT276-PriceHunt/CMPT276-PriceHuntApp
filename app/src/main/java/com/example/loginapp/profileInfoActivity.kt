package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.Database.LoginInfoDatabaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

import com.example.loginapp.LoginActivity.Companion.username

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
    lateinit var buttonSignOut: Button

    lateinit var db: LoginInfoDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profileinfo)
        buttonEdit = findViewById(R.id.btnEdit)
        buttonSignOut = findViewById(R.id.btnSignOut)
        db = LoginInfoDatabaseHelper(this)

        firstname = findViewById<TextView>(R.id.fname)
        lastname = findViewById<TextView>(R.id.lname)
        email = findViewById<TextView>(R.id.email)
        phonenum = findViewById<TextView>(R.id.phone)
        city = findViewById<TextView>(R.id.city)
        prov = findViewById<TextView>(R.id.prov)
        address = findViewById<TextView>(R.id.address)
        postcode = findViewById<TextView>(R.id.postcode)

        val info = db.readUserInfo(username)
        firstname.text = info?.fname
        lastname.text = info?.lname
        email.text = info?.email
        phonenum.text = info?.phone.toString()
        city.text = info?.city
        prov.text = info?.prov
        address.text = info?.address
        postcode.text = info?.postal



        buttonEdit.setOnClickListener{
            val mainActIntent = Intent(this, profileEditActivity::class.java)
            startActivity(mainActIntent)
        }

        buttonSignOut.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Sign Out")
            alertDialogBuilder.setMessage("Are you sure you want to sign out?")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            alertDialogBuilder.setNeutralButton("Cancel") { _, _ ->
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
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