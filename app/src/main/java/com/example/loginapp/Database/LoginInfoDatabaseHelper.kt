package com.example.loginapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class LoginInfoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "accounts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "accounts"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_NAME1 = "Profile"
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
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_USERNAME TEXT PRIMARY KEY, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)

        val createTableQuery1 = "CREATE TABLE $TABLE_NAME1" +
                "($COLUMN_USERNAME TEXT PRIMARY KEY, $COLUMN_FIRST_NAME TEXT, $COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT, $COLUMN_PHONE TEXT, $COLUMN_CITY TEXT, $COLUMN_PROVINCE TEXT, " +
                "$COLUMN_ADDRESS TEXT, $COLUMN_POSTAL TEXT)"
        db?.execSQL(createTableQuery1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)

        val dropTableQuery1 = "DROP TABLE IF EXISTS $TABLE_NAME1"
        db?.execSQL(dropTableQuery1)
        onCreate(db)
    }

    fun insertLoginInfo(loginInfo: loginInfo) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, loginInfo.username)
            put(COLUMN_PASSWORD, loginInfo.password)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun checkLoginInfo(username: String, password: String) : Boolean{
        val db = readableDatabase
        // query checks for any row that matches username and password
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        // pass in the username and password to check
        val cursor = db.rawQuery(query, arrayOf(username, password))

        // checks if the cursor has any rows, this means user has inputted proper username and password
        val found = cursor.count > 0
        cursor.close()
        db.close()
        // return boolean
        return found

    }

    fun insertUserInfo(userInfo: UserInfo){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_USERNAME, userInfo.username)
            put(COLUMN_FIRST_NAME, userInfo.fname)
            put(COLUMN_LAST_NAME, userInfo.lname)
            put(COLUMN_EMAIL, userInfo.email)
            put(COLUMN_PHONE, userInfo.phone)
            put(COLUMN_CITY, userInfo.city)
            put(COLUMN_PROVINCE, userInfo.prov)
            put(COLUMN_ADDRESS, userInfo.address)
            put(COLUMN_POSTAL, userInfo.postal)
        }
        db.insert(TABLE_NAME1, null, values)
        db.close()
    }

    fun readUserInfo(username: String): UserInfo? {
        val profileInfo = UserInfo(username, "", "", "", "", "", "", "", "")
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME1 WHERE $COLUMN_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        cursor.moveToFirst()

        profileInfo.fname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME))
        profileInfo.lname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
        profileInfo.email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
        profileInfo.phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
        profileInfo.city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CITY))
        profileInfo.prov = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROVINCE))
        profileInfo.address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))
        profileInfo.postal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTAL))

        cursor.close()
        db.close()
        return profileInfo
    }

    fun updateUserInfo(userInfo: UserInfo){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_FIRST_NAME, userInfo.fname)
            put(COLUMN_LAST_NAME, userInfo.lname)
            put(COLUMN_EMAIL, userInfo.email)
            put(COLUMN_PHONE, userInfo.phone)
            put(COLUMN_CITY, userInfo.city)
            put(COLUMN_PROVINCE, userInfo.prov)
            put(COLUMN_ADDRESS, userInfo.address)
            put(COLUMN_POSTAL, userInfo.postal)
        }
        val whereClause = "$COLUMN_USERNAME = ?"
        val whereArgs = arrayOf(userInfo.username)
        db.update(TABLE_NAME1, values, whereClause, whereArgs)
        db.close()
    }

}
