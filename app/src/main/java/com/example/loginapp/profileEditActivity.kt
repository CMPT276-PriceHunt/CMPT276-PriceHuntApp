package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.Database.LoginInfoDatabaseHelper

import com.example.loginapp.Database.UserInfo
import com.example.loginapp.LoginActivity.Companion.username



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
    lateinit var buttonDelete: Button

    lateinit var db: LoginInfoDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileedit)


        editTextFirstName = findViewById(R.id.etFirstName)
        editTextLastName = findViewById(R.id.etLastName)
        editTextEmailAddress = findViewById(R.id.etEmailAddress)
        editTextPhoneNumber = findViewById(R.id.etPhoneNumber)
        editTextCity = findViewById(R.id.etCity)
        editTextProvince = findViewById(R.id.etProvince)
        editTextAddress = findViewById(R.id.etStreetAddress)
        editTextPostalCode = findViewById(R.id.etPostalCode)

        buttonSave = findViewById(R.id.btnSave)
        buttonBack = findViewById(R.id.btnBack)
        buttonClear = findViewById(R.id.btnClear)
        buttonDelete = findViewById(R.id.btnDelete)
        db = LoginInfoDatabaseHelper(this)

        // read the info from tables, and display on screen
        val info = db.readUserInfo(username)
        editTextFirstName.setText(info?.fname)
        editTextLastName.setText(info?.lname)
        editTextEmailAddress.setText(info?.email)
        editTextPhoneNumber.setText(info?.phone)
        editTextCity.setText(info?.city)
        editTextProvince.setText(info?.prov)
        editTextAddress.setText(info?.address)
        editTextPostalCode.setText(info?.postal)

        buttonSave.setOnClickListener {

            val fname = editTextFirstName.text.toString()
            val lname = editTextLastName.text.toString()
            val email = editTextEmailAddress.text.toString()
            val phone =editTextPhoneNumber.text.toString()
            var city = editTextCity.text.toString()
            val province = editTextProvince.text.toString()
            val address = editTextAddress.text.toString()
            val postal = editTextPostalCode.text.toString()

            city += ","

            if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() || city.isEmpty()
                || province.isEmpty() || address.isEmpty() || postal.isEmpty() )
            {
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
            else if (editTextEmailAddress.text.contains("@") == false){
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // after checking all conditions, make changes to the table
            val changeInfo = UserInfo(username,fname, lname, email, phone, city, province, address, postal)
            db.updateUserInfo(changeInfo)
            finish()

            Toast.makeText(this, "Changes have been saved!", Toast.LENGTH_SHORT).show()
            val saveBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(saveBtnIntent)
        }

        buttonBack.setOnClickListener{
            val backBtnIntent = Intent(this, profileInfoActivity::class.java)
            startActivity(backBtnIntent)
        }

        buttonClear.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Clear All Fields")
            alertDialogBuilder.setMessage("Are you sure you want to clear all fields?")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->

                val fname = ""
                val lname = ""
                val email = ""
                val phone = ""
                val city = ""
                val province = ""
                val address = ""
                val postal = ""

                val changeInfo =
                    UserInfo(username, fname, lname, email, phone, city, province, address, postal)
                db.updateUserInfo(changeInfo)
                finish()

                Toast.makeText(this, "All fields have been cleared!", Toast.LENGTH_SHORT).show()
                val clearBtnIntent = Intent(this, profileInfoActivity::class.java)
                startActivity(clearBtnIntent)
            }
            alertDialogBuilder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(this, "Clearing cancelled.", Toast.LENGTH_SHORT).show()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        buttonDelete.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Delete Account")
            alertDialogBuilder.setMessage("Are you sure you want to delete your account? This action cannot be undone.")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                db.deleteUserInfo(username)
                finish()
                Toast.makeText(this, "Account has been deleted!", Toast.LENGTH_SHORT).show()
                val deleteBtnIntent = Intent(this, LoginActivity::class.java)
                startActivity(deleteBtnIntent)
            }
            alertDialogBuilder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(this, "Deletion cancelled.", Toast.LENGTH_SHORT).show()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}