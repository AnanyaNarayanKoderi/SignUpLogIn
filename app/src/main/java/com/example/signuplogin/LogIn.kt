package com.example.signuplogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.signuplogin.data.AppDatabase
import com.example.signuplogin.data.User

class LogIn : AppCompatActivity() {
    private lateinit var loginUsername: EditText;
    private lateinit var loginPassword: EditText;
    private lateinit var loginBtn: Button;
    private lateinit var LogintoSignup: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        loginUsername = findViewById(R.id.login_username);
        loginPassword= findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.loginbtn);
        LogintoSignup = findViewById(R.id.logintosignup);

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "User")
            .allowMainThreadQueries().build()
        val userDao = db.userDao()

        loginBtn.setOnClickListener {
            val tempuser = userDao.getUser(loginUsername.text.toString())
            if(tempuser.password.equals(loginPassword.text.toString())){
                Toast.makeText(baseContext,"Login Success",Toast.LENGTH_SHORT).show()
                val ints = Intent(this, MainActivity::class.java)
                startActivity(ints)
            }
            else{
                Toast.makeText(baseContext,"Login Failed!",Toast.LENGTH_SHORT).show()
            }
        }
        LogintoSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    }
