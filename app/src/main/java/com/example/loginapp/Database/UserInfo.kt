package com.example.loginapp.Database

data class UserInfo(val username: String, val fname: String, val lname: String,
                    val address: String, val city: String, val prov: String,
                    val postal: String, val email: String, val phone: Int) {
}
