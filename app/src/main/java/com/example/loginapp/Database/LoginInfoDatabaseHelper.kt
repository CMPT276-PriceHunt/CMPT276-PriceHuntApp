package com.example.loginapp.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class LoginInfoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "accounts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "accounts"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?){
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_USERNAME TEXT PRIMARY KEY, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertLoginInfo(loginInfo: loginInfo){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_USERNAME, loginInfo.username)
            put(COLUMN_PASSWORD, loginInfo.password)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}