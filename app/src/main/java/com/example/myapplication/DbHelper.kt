package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,"appDB",factory,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query ="CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, pass TEXT, mail TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
    fun addUser(user:User){
        val values = ContentValues()
        values.put("login", user.login)
        values.put("pass", user.pass)
        values.put("mail", user.mail)

        val db=this.writableDatabase
        db.insert("users",null, values)

        db.close()
    }
    fun loginUser(login:String, pass:String):Boolean{
        val db=this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst()
    }
    fun validUser(login:String):Boolean{
        val db=this.readableDatabase
        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login'", null)
        return result.moveToFirst()
    }

}