package com.example.loginapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import com.example.loginapp.Database.LoginInfoDatabaseHelper.Companion

class UserInfoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "accounts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Profile"
        private const val COLUMN_USERNAME = "Username"
        private const val COLUMN_FIRST_NAME = "Fname"
        private const val COLUMN_LAST_NAME = "Lname"
        private const val COLUMN_ADDRESS = "Address"
        private const val COLUMN_CITY = "City"
        private const val COLUMN_PROVINCE = "Province"
        private const val COLUMN_POSTAL = "Postal"
        private const val COLUMN_EMAIL = "Email"
        private const val COLUMN_PHONE = "Phone"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_USERNAME TEXT PRIMARY KEY, $COLUMN_FIRST_NAME TEXT, $COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_ADDRESS TEXT, $COLUMN_CITY TEXT, $COLUMN_PROVINCE TEXT, $COLUMN_POSTAL TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PHONE INTEGER)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUserInfo(userInfo: UserInfo){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_USERNAME, userInfo.username)
            put(COLUMN_FIRST_NAME, userInfo.fname)
            put(COLUMN_LAST_NAME, userInfo.lname)
            put(COLUMN_ADDRESS, userInfo.address)
            put(COLUMN_CITY, userInfo.city)
            put(COLUMN_PROVINCE, userInfo.prov)
            put(COLUMN_POSTAL, userInfo.postal)
            put(COLUMN_EMAIL, userInfo.email)
            put(COLUMN_PHONE, userInfo.phone)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}