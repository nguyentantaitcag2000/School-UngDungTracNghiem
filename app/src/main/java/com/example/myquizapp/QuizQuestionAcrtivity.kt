package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionAcrtivity : AppCompatActivity() {
    private var mCurrentPosition: Int = 1 // Starting with the first question
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private lateinit var tvQuestion: TextView
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var trangThaiSoluongCauHoi: TextView
    private lateinit var progressBar: ProgressBar
    private  var soCauTraLoiDung: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question_acrtivity)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)
        trangThaiSoluongCauHoi = findViewById(R.id.trangThaiSoLuongCauHoi)
        progressBar = findViewById(R.id.myProgressBar)

        val questionList = Constants.getQuestions()
        Toast.makeText(this,"${questionList.size}", Toast.LENGTH_LONG).show()
        mQuestionList = Constants.getQuestions()
        progressBar.max = questionList.size
        setQuestion()


        // Assuming you have 4 options for each question
        tvOptionOne.setOnClickListener { view -> onOptionClicked(view as TextView) }
        tvOptionTwo.setOnClickListener { view -> onOptionClicked(view as TextView) }
        tvOptionThree.setOnClickListener { view -> onOptionClicked(view as TextView) }
        tvOptionFour.setOnClickListener { view -> onOptionClicked(view as TextView) }
    }
    private fun setQuestion() {
        val question = mQuestionList!![mCurrentPosition - 1]
        trangThaiSoluongCauHoi.text = "$mCurrentPosition/${mQuestionList!!.size}"
        // Reset backgrounds for options
        resetOptionBackgrounds()

        // Set the question and options in the UI
        tvQuestion.text = question.question
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun onOptionClicked(view: TextView) {
        when (view.id) {
            R.id.tvOptionOne -> mSelectedOptionPosition = 1
            R.id.tvOptionTwo -> mSelectedOptionPosition = 2
            R.id.tvOptionThree -> mSelectedOptionPosition = 3
            R.id.tvOptionFour -> mSelectedOptionPosition = 4
        }

        if (mSelectedOptionPosition == mQuestionList!![mCurrentPosition - 1].correctAnswer) {
            //Truong hop dung
            val correctColor = ContextCompat.getColor(this, R.color.correct)
            view.setBackgroundColor(correctColor)
            soCauTraLoiDung++
        } else {
            // Truong hop sai
            val wrongColor = ContextCompat.getColor(this, R.color.wrong)
            view.setBackgroundColor(wrongColor)
        }

        // Di chuyen sang cau tiep theo
        mCurrentPosition++
        if (mCurrentPosition <= mQuestionList!!.size) {
            // Sử dụng Handler để thêm delay
            Handler(Looper.getMainLooper()).postDelayed({
                    setQuestion()
                    // Cập nhật giá trị ProgressBar
                    progressBar.progress = mCurrentPosition - 1

            }, 2000) // Delay 2 giây (2000 milliseconds)
        } else {
            // Cập nhật giá trị ProgressBar
            progressBar.progress = mCurrentPosition - 1
            // Sử dụng Handler để thêm delay
            Handler(Looper.getMainLooper()).postDelayed({
                // Chuyen sang intent ket qua
                val intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("result", "$soCauTraLoiDung/${mQuestionList!!.size}")
                startActivity(intent)
                finish()
            }, 2000) // Delay 2 giây (2000 milliseconds)
        }
    }

    private fun resetOptionBackgrounds() {

        val whiteColor = ContextCompat.getColor(this, R.color.white)
        tvOptionOne.setBackgroundColor(whiteColor)
        tvOptionTwo.setBackgroundColor(whiteColor)
        tvOptionThree.setBackgroundColor(whiteColor)
        tvOptionFour.setBackgroundColor(whiteColor)
    }

}