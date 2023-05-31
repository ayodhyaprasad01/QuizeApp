package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var submitButton: Button

    private val questions = arrayOf(
        "1. Which of the following is not OOPS concept in Java?",
        "2. Which of the following is a type of polymorphism in Java?\n",
        "3. When does method overloading is determined?\n",
        "4. Which concept of Java is a way of converting real world objects in terms of class?\n",
        "5. What is it called if an object has its own lifecycle and there is no owner?",
        "6. Which concept of Java is achieved by combining methods and attribute into a class?\n"
    )

    private val options = arrayOf(
        arrayOf("a) Compilation\n", "b) Encapsulation\n", "c) Polymorphism\n", "d) Inheritance\n"),
        arrayOf("a) Multiple polymorphism\n", "b)  Compile time polymorphism\n", "c) Execution time polymorphism\n", "d) Multilevel polymorphism\n"),
        arrayOf("a) At run time\n", "b) At compile time\n", "c) At coding time\n", "d) At execution time\n"),
        arrayOf("a) Inheritance\n", "b) Polymorphism\n", "c) Encapsulation\n", "d) Abstraction\n"),
        arrayOf("a) Association\n","b) Aggregation\n","c) Composition\n","d) Encapsulation\n"),
        arrayOf("a) Encapsulation\n","b) Inheritance\n","c) Polymorphism\n","d) Abstraction\n")
    )

    private val correctAnswers = intArrayOf(1, 2, 3, 4,1,2)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        optionsGroup = findViewById(R.id.optionsGroup)
        submitButton = findViewById(R.id.submitButton)

        showNextQuestion()

        submitButton.setOnClickListener {
            val selectedOption = optionsGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val radioButton = findViewById<RadioButton>(selectedOption)
                val selectedAnswer = optionsGroup.indexOfChild(radioButton) + 1
                checkAnswer(selectedAnswer)
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < questions.size) {
            questionText.text = questions[currentQuestionIndex]

            val option1 = findViewById<RadioButton>(R.id.option1)
            val option2 = findViewById<RadioButton>(R.id.option2)
            val option3 = findViewById<RadioButton>(R.id.option3)
            val option4 = findViewById<RadioButton>(R.id.option4)

            option1.text = options[currentQuestionIndex][0]
            option2.text = options[currentQuestionIndex][1]
            option3.text = options[currentQuestionIndex][2]
            option4.text = options[currentQuestionIndex][3]

            optionsGroup.clearCheck()
        } else {
            showQuizResults()
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {
        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++
        showNextQuestion()
    }

    private fun showQuizResults() {
        val intent = intent
        val name = intent.getStringExtra("name")

        Toast.makeText(
            this,
            "Quiz completed!\nName: $name\nScore: $score/${questions.size}",
            Toast.LENGTH_LONG
        ).show()
    }
}
