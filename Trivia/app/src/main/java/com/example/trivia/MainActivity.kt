package com.example.trivia

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var option1RadioButton: RadioButton
    private lateinit var option2RadioButton: RadioButton
    private lateinit var option3RadioButton: RadioButton
    private lateinit var option4RadioButton: RadioButton
    private lateinit var submitButton: Button
    private lateinit var scoreTextView: TextView

    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        Question(
            "What is the capital of France?",
            listOf("London", "Berlin", "Paris", "Madrid"),
            2
        ),
        Question(
            "Which planet is known as the Red Planet?",
            listOf("Venus", "Mars", "Jupiter", "Saturn"),
            1
        ),
        Question(
            "What is the largest mammal in the world?",
            listOf("African Elephant", "Blue Whale", "Giraffe", "Polar Bear"),
            1
        ),
        Question(
            "Who painted the Mona Lisa?",
            listOf("Vincent van Gogh", "Pablo Picasso", "Michelangelo", "Leonardo da Vinci"),
            3
        ),
        Question(
            "What is the chemical symbol for Gold?",
            listOf("Ag", "Fe", "Au", "Cu"),
            2
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        option1RadioButton = findViewById(R.id.option1RadioButton)
        option2RadioButton = findViewById(R.id.option2RadioButton)
        option3RadioButton = findViewById(R.id.option3RadioButton)
        option4RadioButton = findViewById(R.id.option4RadioButton)
        submitButton = findViewById(R.id.submitButton)
        scoreTextView = findViewById(R.id.scoreTextView)

        // Display first question
        displayQuestion()

        // Set up submit button click listener
        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question

        option1RadioButton.text = currentQuestion.options[0]
        option2RadioButton.text = currentQuestion.options[1]
        option3RadioButton.text = currentQuestion.options[2]
        option4RadioButton.text = currentQuestion.options[3]

        optionsRadioGroup.clearCheck()
        submitButton.isEnabled = true
    }

    private fun checkAnswer() {
        val selectedId = optionsRadioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        val correctAnswer = questions[currentQuestionIndex].correctAnswer
        val selectedAnswerIndex = when (selectedId) {
            R.id.option1RadioButton -> 0
            R.id.option2RadioButton -> 1
            R.id.option3RadioButton -> 2
            R.id.option4RadioButton -> 3
            else -> -1
        }

        if (selectedAnswerIndex == correctAnswer) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            displayQuestion()
        } else {
            // Quiz completed
            questionTextView.text = "Quiz completed!"
            optionsRadioGroup.visibility = android.view.View.GONE
            submitButton.visibility = android.view.View.GONE
            scoreTextView.text = "Your score: $score out of ${questions.size}"
        }
    }
}

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)