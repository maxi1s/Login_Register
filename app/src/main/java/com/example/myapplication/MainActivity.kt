package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteCursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userMail: EditText = findViewById(R.id.user_mail)
        val userPass: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)
        val delete: Button = findViewById(R.id.buttonDel)

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()
            val mail = userMail.text.toString().trim()
            val db1 = DbHelper(this, null)
            if (login == " " || pass == "" || mail == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else if (!db1.validUser(login)) {
                val user = User(login, pass, mail)
                db1.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()
                userLogin.text.clear()
                userMail.text.clear()
                userPass.text.clear()
            } else Toast.makeText(
                this,
                "ОШИБКА! Пользователь $login уже существует",
                Toast.LENGTH_LONG
            ).show()


        }
        val linkToAuth: TextView = findViewById(R.id.link_ToAuth)
        linkToAuth.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()
            val mail = userMail.text.toString().trim()
            val db1 = DbHelper(this, null)
            this.deleteDatabase("appDB")
        }

    }}
