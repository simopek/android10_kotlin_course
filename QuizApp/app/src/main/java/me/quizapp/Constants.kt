package me.quizapp

object Constants {

    private const val WHAT_IS_THIS__QUESTION = "What is this?"

    fun getQuestions(): List<Question> {

        val questions = ArrayList<Question>()

        val question1 = Question(
            id = 1,
            question = WHAT_IS_THIS__QUESTION,
            image = R.drawable.guitar,
            optionOne = "A car",
            optionTwo = "An animal",
            optionThree = "A guitar",
            optionFour = "???",
            correctOption =  3
        )
        questions.add(question1)

        val question2 = Question(
            id = 2,
            question = WHAT_IS_THIS__QUESTION,
            image = R.drawable.camaro_car,
            optionOne = "A car",
            optionTwo = "A bus",
            optionThree = "A guitar",
            optionFour = "???",
            correctOption =  1
        )
        questions.add(question2)

        val question3 = Question(
            id = 3,
            question = WHAT_IS_THIS__QUESTION,
            image = R.drawable.sun,
            optionOne = "A truck",
            optionTwo = "A bus",
            optionThree = "A guitar",
            optionFour = "The sun",
            correctOption =  4
        )
        questions.add(question3)

        return questions
    }
}