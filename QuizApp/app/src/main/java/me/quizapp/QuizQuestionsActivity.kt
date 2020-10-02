package me.quizapp

import android.content.Intent
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

    companion object {

        const val NUM_CORRECT_ANSWERS = "NUM_CORRECT_ANSWERS"
        const val NUM_QUESTIONS = "NUM_QUESTIONS"
    }

    private val questionAnswers: HashMap<Int, Boolean> = HashMap()
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
        this.submitButton.text = if (currentQuestionIdx < questions.size - 1) "Submit" else "Finish"

        resetDefaultStyleOnQuestionOptions()
    }

    private fun initSubmitButton() {

        this.submitButton.setOnClickListener(fun(_: View) {

            if (selectedQuestionOption == null) {
                Toast.makeText(this, "Please, choose an option", Toast.LENGTH_SHORT).show()
            } else {

                val question = questions[currentQuestionIdx]
                if (question.correctOption == selectedQuestionOption) {

                    updateAnswers(currentQuestionIdx, true)

                    currentQuestionIdx++
                    if (currentQuestionIdx >= questions.size) {

                        openFinishActivity()
                        return
                    }
                    refreshView()

                } else {
                    Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show()
                    updateAnswers(currentQuestionIdx, false)
                }
            }

        })
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

    private fun updateAnswers(currentQuestionIdx: Int, answerIsCorrect: Boolean) {

        if (currentQuestionIdx in questionAnswers) {
            // we just save the first answer
            return
        }

        questionAnswers[currentQuestionIdx] = answerIsCorrect
    }

    private fun openFinishActivity() {

        val numberOfCorrectAnswers = questionAnswers.filter { it -> it.value }.count()

        val intent = Intent(this, FinishActivity::class.java)
        intent.putExtra(NUM_CORRECT_ANSWERS, numberOfCorrectAnswers)
        intent.putExtra(NUM_QUESTIONS, questions.size)
        startActivity(intent)
        finish()
    }
}
