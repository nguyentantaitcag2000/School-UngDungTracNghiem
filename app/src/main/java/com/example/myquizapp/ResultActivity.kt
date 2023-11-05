package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = intent.getStringExtra("result")
        val resultMessage: TextView = findViewById(R.id.result)
        val btnBack: Button = findViewById(R.id.btnBack)
        resultMessage.text = result
        btnBack.setOnClickListener({
            val intent = Intent(this, QuizQuestionAcrtivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}