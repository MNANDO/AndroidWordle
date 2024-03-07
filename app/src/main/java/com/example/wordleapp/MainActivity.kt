package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var textInput: EditText
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val resetButton = findViewById<Button>(R.id.restButton)

        textInput = findViewById(R.id.textInput)

        val r1c1 = findViewById<TextView>(R.id.r1c1)
        val r1c2 = findViewById<TextView>(R.id.r1c2)
        val r1c3 = findViewById<TextView>(R.id.r1c3)
        val r1c4 = findViewById<TextView>(R.id.r1c4)
        val r2c1 = findViewById<TextView>(R.id.r2c1)
        val r2c2 = findViewById<TextView>(R.id.r2c2)
        val r2c3 = findViewById<TextView>(R.id.r2c3)
        val r2c4 = findViewById<TextView>(R.id.r2c4)
        val r3c1 = findViewById<TextView>(R.id.r3c1)
        val r3c2 = findViewById<TextView>(R.id.r3c2)
        val r3c3 = findViewById<TextView>(R.id.r3c3)
        val r3c4 = findViewById<TextView>(R.id.r3c4)

        resetButton.setOnClickListener {
            r1c1.text = "X"
            r1c2.text = "X"
            r1c3.text = "X"
            r1c4.text = "X"
            r2c1.text = "X"
            r2c2.text = "X"
            r2c3.text = "X"
            r2c4.text = "X"
            r3c1.text = "X"
            r3c2.text = "X"
            r3c3.text = "X"
            r3c4.text = "X"
            textInput.text.clear()
            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            guessCount = 0
        }

        submitButton.setOnClickListener {
            Toast.makeText(it.context, wordToGuess, Toast.LENGTH_SHORT).show()
            val guessResult = checkGuess(textInput.text.toString())
            when (guessCount) {
                0 -> {
                    r1c1.text = guessResult[0].toString()
                    r1c2.text = guessResult[1].toString()
                    r1c3.text = guessResult[2].toString()
                    r1c4.text = guessResult[3].toString()
                    textInput.text.clear()
                }
                1 -> {
                    r2c1.text = guessResult[0].toString()
                    r2c2.text = guessResult[1].toString()
                    r2c3.text = guessResult[2].toString()
                    r2c4.text = guessResult[3].toString()
                    textInput.text.clear()
                }
                2 -> {
                    r3c1.text = guessResult[0].toString()
                    r3c2.text = guessResult[1].toString()
                    r3c3.text = guessResult[2].toString()
                    r3c4.text = guessResult[3].toString()
                    textInput.text.clear()
                }
                else -> {
                    textInput.text.clear()
                    Toast.makeText(it.context, "You have ran out of guesses", Toast.LENGTH_SHORT).show()
                }
            }
            guessCount++
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}