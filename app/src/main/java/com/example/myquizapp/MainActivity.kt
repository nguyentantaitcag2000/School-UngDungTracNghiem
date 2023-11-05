package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val elName: EditText = findViewById(R.id.etName)
        btnStart.setOnClickListener{
            if (elName.text.isEmpty())
            {
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this,QuizQuestionAcrtivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}