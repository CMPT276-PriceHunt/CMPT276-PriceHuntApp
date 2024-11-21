package com.example.loginapp.Database

data class UserInfo(val username: String, var fname: String, var lname: String,
                    var email: String, var phone: String,
                    var city: String, var prov: String,
                    var address: String, var postal: String) {
}
