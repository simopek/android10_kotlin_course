package me.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var currentQuestionIdx = 0
    private var selectedQuestionOption: Int? = null
    private var questions = Constants.getQuestions()
    private var questionOptionTextViewList: ArrayList<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        setQuestionOptionTextViewList()
        initQuestionOptionClickListener()
        initSubmitButton()
        refreshView()
    }

    private fun setQuestionOptionTextViewList() {

        this.questionOptionTextViewList = ArrayList<TextView>()
        questionOptionTextViewList!!.add(this.questionOption1TextView)
        questionOptionTextViewList!!.add(this.questionOption2TextView)
        questionOptionTextViewList!!.add(this.questionOption3TextView)
        questionOptionTextViewList!!.add(this.questionOption4TextView)
    }

    private fun initQuestionOptionClickListener() {

        for (questionOptionTextView in this.questionOptionTextViewList!!) {
            questionOptionTextView.setOnClickListener(this)
        }
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

        resetDefaultStyleOnQuestionOptions()
    }

    private fun initSubmitButton() {

        this.submitButton.setOnClickListener() {

            if (selectedQuestionOption == null) {
                Toast.makeText(this, "Please, choose an option", Toast.LENGTH_SHORT).show()
            } else {

                val question = questions[currentQuestionIdx]
                if (question.correctOption == selectedQuestionOption) {

                    currentQuestionIdx++
                    if (currentQuestionIdx >= questions.size) {
                        Toast.makeText(this, "YOU WIN!!!", Toast.LENGTH_SHORT).show()
                        resetActivityState()
                    }
                    refreshView()

                } else {
                    Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun resetActivityState() {
        
        this.selectedQuestionOption = null
        this.currentQuestionIdx = 0
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.questionOption1TextView -> {
                onQuestionOptionClick(this.questionOption1TextView, 1)
            }
            R.id.questionOption2TextView -> {
                onQuestionOptionClick(this.questionOption2TextView, 2)
            }
            R.id.questionOption3TextView -> {
                onQuestionOptionClick(this.questionOption3TextView, 3)
            }
            R.id.questionOption4TextView -> {
                onQuestionOptionClick(this.questionOption4TextView, 4)
            }
        }
    }

    private fun onQuestionOptionClick(questionOptionTextView: TextView, chosenOption: Int) {

        this.selectedQuestionOption = chosenOption

        resetDefaultStyleOnQuestionOptions()
        setSelectedStyleOnQuestionOption(questionOptionTextView)
    }

    private fun resetDefaultStyleOnQuestionOptions() {

        for (questionOptionTextView in this.questionOptionTextViewList!!) {

            questionOptionTextView.typeface = Typeface.DEFAULT
            questionOptionTextView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorCustomBlack
                )
            )
            questionOptionTextView.background =
                ContextCompat.getDrawable(this, R.drawable.question_option_background)
        }
    }

    private fun setSelectedStyleOnQuestionOption(questionOptionTextView: TextView) {

        questionOptionTextView.typeface = Typeface.DEFAULT_BOLD
        questionOptionTextView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
        questionOptionTextView.background =
            ContextCompat.getDrawable(this, R.drawable.selected_question_option_background)
    }
}
