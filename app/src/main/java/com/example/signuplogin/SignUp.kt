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
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUp : AppCompatActivity() {
    private lateinit var signupUsername:EditText;
    private lateinit var signupPassword:EditText;
    private lateinit var signupBtn: Button;
    private lateinit var SignToLog:TextView;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupBtn = findViewById(R.id.signupbtn);
        SignToLog = findViewById(R.id.singup_login);

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "User")
            .allowMainThreadQueries().build()
        val urDao = db.userDao()

        signupBtn.setOnClickListener {
            if (signupPassword.text.toString().length < 8 && !isValidPassword(signupPassword.text.toString())) {
                Toast.makeText(baseContext, "Invalid Password", Toast.LENGTH_SHORT).show()
            }

            else {
                val tempuser = User(signupUsername.text.toString(), signupPassword.text.toString())
                urDao.insertuser(tempuser)
                Toast.makeText(baseContext, "Sign Up successful", Toast.LENGTH_SHORT).show()
            }

        }
        SignToLog.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }

         private fun isValidPassword(password: String?): Boolean {
             val pattern: Pattern
             val matcher: Matcher
             val password_pattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
             pattern=Pattern.compile(password_pattern)
             matcher=pattern.matcher(password)
             return matcher.matches()
         }
}