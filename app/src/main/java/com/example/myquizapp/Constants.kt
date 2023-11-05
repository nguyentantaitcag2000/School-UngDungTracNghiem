package com.example.myquizapp

import android.util.Log
import android.widget.Toast

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val q1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.baseline_flag_24,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        val q2 = Question(
            1,
            "1 + 1 = ?",
            R.drawable.baseline_flag_24,
            "4",
            "3",
            "2",
            "5",
            3
        )
        questionList.add(q1)
        questionList.add(q2)

        return questionList
    }
}