package com.example.wordle

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wordle.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // on below line creating a variable for activity binding.
    private lateinit var binding: ActivityMainBinding

    // Create a list of 5-letter words
    private val words = listOf(
        "apple", "grape", "mango", "lemon", "peach", "plaza", "flame", "stone",
        "baker", "liver", "ocean", "vivid", "track", "sword", "green", "blaze",
        "scout", "happy", "drink", "fruit", "sight", "plumb", "bride", "latch",
        "glove", "jolly", "piano", "charm", "chose", "shine", "blend", "plumb",
        "draft", "waste", "zebra", "sailo", "rainy", "mason", "glint", "sweep",
        "block", "thorn", "grasp", "clamp", "chalk", "leads", "round", "stark",
        "racer", "sweet", "haste", "brace", "march"
    )

    // Variable to store the word to be guessed
    private lateinit var WORD: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tryAgainBtn.setOnClickListener {
            resetGame()
        }

        // Select a random word from the list
        WORD = words[Random.nextInt(words.size)]
        // on below line calling method to pass focus to next edit text.
        keepPassingFocus()

        // on below line adding text change listener
        binding.edt15.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt11,
                        binding.edt12,
                        binding.edt13,
                        binding.edt14,
                        binding.edt15
                    )
                }
            }

        })
        binding.edt25.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt21,
                        binding.edt22,
                        binding.edt23,
                        binding.edt24,
                        binding.edt25
                    )
                }
            }

        })
        binding.edt35.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt31,
                        binding.edt32,
                        binding.edt33,
                        binding.edt34,
                        binding.edt35
                    )
                }
            }

        })
        binding.edt45.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt41,
                        binding.edt42,
                        binding.edt43,
                        binding.edt44,
                        binding.edt45
                    )
                }
            }

        })
        binding.edt55.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt51,
                        binding.edt52,
                        binding.edt53,
                        binding.edt54,
                        binding.edt55
                    )
                }
            }

        })
        binding.edt65.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    // on below line calling validate row to
                    // check the word entered in that row.
                    validateRow(
                        binding.edt61,
                        binding.edt62,
                        binding.edt63,
                        binding.edt64,
                        binding.edt65
                    )
                }
            }

        })
    }


    private fun makeGameInactive() {
        // on below line disabling all
        // edit text to make game inactive.
        binding.edt11.isEnabled = false
        binding.edt12.isEnabled = false
        binding.edt13.isEnabled = false
        binding.edt14.isEnabled = false
        binding.edt15.isEnabled = false
        binding.edt21.isEnabled = false
        binding.edt22.isEnabled = false
        binding.edt23.isEnabled = false
        binding.edt24.isEnabled = false
        binding.edt25.isEnabled = false
        binding.edt31.isEnabled = false
        binding.edt32.isEnabled = false
        binding.edt33.isEnabled = false
        binding.edt34.isEnabled = false
        binding.edt35.isEnabled = false
        binding.edt41.isEnabled = false
        binding.edt42.isEnabled = false
        binding.edt43.isEnabled = false
        binding.edt44.isEnabled = false
        binding.edt45.isEnabled = false
        binding.edt51.isEnabled = false
        binding.edt52.isEnabled = false
        binding.edt53.isEnabled = false
        binding.edt54.isEnabled = false
        binding.edt61.isEnabled = false
        binding.edt62.isEnabled = false
        binding.edt63.isEnabled = false
        binding.edt64.isEnabled = false
        binding.edt65.isEnabled = false
    }

    private fun validateRow(
        edt1: EditText,
        edt2: EditText,
        edt3: EditText,
        edt4: EditText,
        edt5: EditText
    ) {
        // on below line creating variables
        // to get text from edit texts.
        val edt1Txt = edt1.text.toString()
        val edt2Txt = edt2.text.toString()
        val edt3Txt = edt3.text.toString()
        val edt4Txt = edt4.text.toString()
        val edt5Txt = edt5.text.toString()

        // on below line creating variables
        // to get each char from word.
        val w1 = WORD[0].toString()
        val w2 = WORD[1].toString()
        val w3 = WORD[2].toString()
        val w4 = WORD[3].toString()
        val w5 = WORD[4].toString()

        // on below line comparing if text entered in edt is equal to other words.
        if (edt1Txt == w2 || edt1Txt == w3 || edt1Txt == w4 || edt1Txt == w5) {
            // on below line changing background color of that edt.
            edt1.setBackgroundColor(Color.parseColor("#ffff00"))
        }
        // on below line comparing if text entered in edt is equal to other words.
        if (edt2Txt == w1 || edt2Txt == w3 || edt2Txt == w4 || edt2Txt == w5) {
            // on below line changing background color of that edt.
            edt2.setBackgroundColor(Color.parseColor("#ffff00"))
        }
        // on below line comparing if text entered in edt is equal to other words.
        if (edt3Txt == w1 || edt3Txt == w2 || edt3Txt == w4 || edt3Txt == w5) {
            // on below line changing background color of that edt.
            edt3.setBackgroundColor(Color.parseColor("#ffff00"))
        }
        // on below line comparing if text entered in edt is equal to other words.
        if (edt4Txt == w1 || edt4Txt == w2 || edt4Txt == w3 || edt4Txt == w5) {
            // on below line changing background color of that edt.
            edt4.setBackgroundColor(Color.parseColor("#ffff00"))
        }
        // on below line comparing if text entered in edt is equal to other words.
        if (edt5Txt == w1 || edt5Txt == w2 || edt5Txt == w3 || edt5Txt == w4) {
            // on below line changing background color of that edt.
            edt5.setBackgroundColor(Color.parseColor("#ffff00"))
        }

        // on below line checking if word is equal to text in edt
        if (edt1Txt == w1) {
            // on below line changing background color of that edt.
            edt1.setBackgroundColor(Color.parseColor("#33cc33"))
        }
        // on below line checking if word is equal to text in edt
        if (edt2Txt == w2) {
            // on below line changing background color of that edt.
            edt2.setBackgroundColor(Color.parseColor("#33cc33"))
        }
        // on below line checking if word is equal to text in edt
        if (edt3Txt == w3) {
            // on below line changing background color of that edt.
            edt3.setBackgroundColor(Color.parseColor("#33cc33"))
        }
        // on below line checking if word is equal to text in edt
        if (edt4Txt == w4) {
            // on below line changing background color of that edt.
            edt4.setBackgroundColor(Color.parseColor("#33cc33"))
        }
        // on below line checking if word is equal to text in edt
        if (edt5Txt == w5) {
            // on below line changing background color of that edt.
            edt5.setBackgroundColor(Color.parseColor("#33cc33"))
        }

        // on below line checking if entered by users is present
        // in the word which user has to find.
        if (edt1Txt != w1 && edt1Txt != w2 && edt1Txt != w3 && edt1Txt != w4 && edt1Txt != w5) {
            // on below line changing background color of that edt.
            edt1.setBackgroundColor(Color.parseColor("#ff3333"))
        }

        if (edt2Txt != w1 && edt2Txt != w2 && edt2Txt != w3 && edt2Txt != w4 && edt2Txt != w5) {
            // on below line changing background color of that edt.
            edt2.setBackgroundColor(Color.parseColor("#ff3333"))
        }

        if (edt3Txt != w1 && edt3Txt != w2 && edt3Txt != w3 && edt3Txt != w4 && edt3Txt != w5) {
            // on below line changing background color of that edt.
            edt3.setBackgroundColor(Color.parseColor("#ff3333"))
        }

        if (edt4Txt != w1 && edt4Txt != w2 && edt4Txt != w3 && edt4Txt != w4 && edt4Txt != w5) {
            // on below line changing background color of that edt.
            edt4.setBackgroundColor(Color.parseColor("#ff3333"))
        }

        if (edt5Txt != w1 && edt5Txt != w2 && edt5Txt != w3 && edt5Txt != w4 && edt5Txt != w5) {
            // on below line changing background color of that edt.
            edt5.setBackgroundColor(Color.parseColor("#ff3333"))
        }

        // below method is called if user is able to guess the word.
        if (edt1Txt == w1 && edt2Txt == w2 && edt3Txt == w3 && edt4Txt == w4 && edt5Txt == w5) {
            // on below line setting text and changing the visibility of text.
            binding.idTVCongo.text = "Congratulations, you have guessed the right word."
            binding.idTVCongo.visibility = View.VISIBLE
            // on below line calling below method to make game inactive.
            makeGameInactive()
            // on below line displaying toast message.
            Toast.makeText(
                applicationContext,
                "Congratulations, you have guessed the right word..",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // below method is called if user is not able to guess the word.
        if (edt5.id == R.id.edt_65) {
            binding.idTVCongo.text = "Sorry you couldn't guess the word.\nThe word was $WORD"
            binding.idTVCongo.visibility = View.VISIBLE
            binding.tryAgainBtn.visibility = View.VISIBLE  // Show the try again button
            makeGameInactive()
            Toast.makeText(
                applicationContext,
                "Sorry you couldn't guess the word.\nThe word was $WORD",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun keepPassingFocus() {
        // on below line calling method pass focus to next
        // to pass focus to next edt for row 1.
        passFocusToNextEdt(binding.edt11, binding.edt12)
        passFocusToNextEdt(binding.edt12, binding.edt13)
        passFocusToNextEdt(binding.edt13, binding.edt14)
        passFocusToNextEdt(binding.edt14, binding.edt15)

        // on below line calling method pass focus to next
        // to pass focus to next edt for row 2.
        passFocusToNextEdt(binding.edt21, binding.edt22)
        passFocusToNextEdt(binding.edt22, binding.edt23)
        passFocusToNextEdt(binding.edt23, binding.edt24)
        passFocusToNextEdt(binding.edt24, binding.edt25)

        // on below line calling method pass focus to next
        // to pass focus to next edt for row 3.
        passFocusToNextEdt(binding.edt31, binding.edt32)
        passFocusToNextEdt(binding.edt32, binding.edt33)
        passFocusToNextEdt(binding.edt33, binding.edt34)
        passFocusToNextEdt(binding.edt34, binding.edt35)

        // on below line calling method pass focus to next
        // to pass focus to next edt for row 4.
        passFocusToNextEdt(binding.edt41, binding.edt42)
        passFocusToNextEdt(binding.edt42, binding.edt43)
        passFocusToNextEdt(binding.edt43, binding.edt44)
        passFocusToNextEdt(binding.edt44, binding.edt45)

        // on below line calling method pass focus to next
        // to pass focus to next edt for row 5.
        passFocusToNextEdt(binding.edt51, binding.edt52)
        passFocusToNextEdt(binding.edt52, binding.edt53)
        passFocusToNextEdt(binding.edt53, binding.edt54)
        passFocusToNextEdt(binding.edt54, binding.edt55)

        // on below line calling method pass focus to next
        // to pass focus to next edt for row 6.
        passFocusToNextEdt(binding.edt61, binding.edt62)
        passFocusToNextEdt(binding.edt62, binding.edt63)
        passFocusToNextEdt(binding.edt63, binding.edt64)
        passFocusToNextEdt(binding.edt64, binding.edt65)
    }

    private fun resetGame() {
        // Select a new random word
        WORD = words[Random.nextInt(words.size)]

        // Clear all EditTexts and reset their backgrounds
        val allEditTexts = listOf(
            binding.edt11, binding.edt12, binding.edt13, binding.edt14, binding.edt15,
            binding.edt21, binding.edt22, binding.edt23, binding.edt24, binding.edt25,
            binding.edt31, binding.edt32, binding.edt33, binding.edt34, binding.edt35,
            binding.edt41, binding.edt42, binding.edt43, binding.edt44, binding.edt45,
            binding.edt51, binding.edt52, binding.edt53, binding.edt54, binding.edt55,
            binding.edt61, binding.edt62, binding.edt63, binding.edt64, binding.edt65
        )

        allEditTexts.forEach { editText ->
            editText.setText("")
            editText.setBackgroundResource(R.drawable.back_edt)
            editText.isEnabled = true
        }

        // Hide the congratulations text and try again button
        binding.idTVCongo.visibility = View.GONE
        binding.tryAgainBtn.visibility = View.GONE

        // Set focus to the first EditText
        binding.edt11.requestFocus()
    }

    private fun passFocusToNextEdt(edt1: EditText, edt2: EditText) {
        // on below line we are passing focus to
        // next edt is previous one is filled.
        edt1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    edt2.requestFocus()
                }
            }

        })
    }
}
