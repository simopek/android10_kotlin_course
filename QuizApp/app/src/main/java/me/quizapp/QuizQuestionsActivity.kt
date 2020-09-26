package me.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {

    private var currentQuestionIdx = 0
    private var questions = Constants.getQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        initSubmitButton()
        refreshView()
    }

    private fun refreshView() {

        var currQuestion = questions[currentQuestionIdx]

        this.questionTextView.text = currQuestion.question
        this.questionImage.setImageResource(currQuestion.image)
        this.questionOption1TextView.text = currQuestion.optionOne
        this.questionOption2TextView.text = currQuestion.optionTwo
        this.questionOption3TextView.text = currQuestion.optionThree
        this.questionOption4TextView.text = currQuestion.optionFour
        this.progressBar.progress = currentQuestionIdx + 1
        this.progressTextView.text = "${(currentQuestionIdx + 1)}/${questions.size}"
    }

    private fun initSubmitButton() {

        this.submitButton.setOnClickListener() {

            // TODO add logic
            currentQuestionIdx++

            if (currentQuestionIdx >= questions.size) {
                currentQuestionIdx = 0
            }

            refreshView()
        }
    }
}