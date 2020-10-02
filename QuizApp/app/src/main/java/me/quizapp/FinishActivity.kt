package me.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val numCorrectAnswers = intent.getIntExtra(QuizQuestionsActivity.NUM_CORRECT_ANSWERS, -1)
        val numQuestions = intent.getIntExtra(QuizQuestionsActivity.NUM_QUESTIONS, -1)

        if (numCorrectAnswers == -1 || numQuestions == -1) {
            this.infoTextView.text = "<no data>"
        } else {
            this.infoTextView.text = "${numCorrectAnswers}/${numQuestions} correct answers"
        }

        this.restartButton.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}