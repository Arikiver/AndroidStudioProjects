package com.example.scrabble

import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scrabble.R
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var wordList: List<String>
    private lateinit var sourceWord: String  // The 5-6 letter word we'll use for letters
    private var remainingChances = 3
    private val threeLetterSlots1 = mutableListOf<TextView>()
    private val threeLetterSlots2 = mutableListOf<TextView>()
    private val fourLetterSlots = mutableListOf<TextView>()
    private val letterTiles = mutableListOf<TextView>()

    private data class DragData(val view: TextView, val isSourceTile: Boolean)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadWordsFromFile()
        initializeUI()
        setupSourceWordAndLetters()
        setupDragAndDrop()
    }

    private fun loadWordsFromFile() {
        val inputStream = resources.openRawResource(R.raw.words)
        val reader = BufferedReader(InputStreamReader(inputStream))
        // Load all words and convert them to lowercase
        val allWords = reader.readLines().map { it.lowercase() }
        wordList = allWords.filter { it.length in 3..4 }  // Only 3-4 letter words for valid plays
        // Get 5-6 letter words for source word selection
        val sourceWords = allWords.filter { it.length in 5..6 }
        sourceWord = sourceWords.random()
        reader.close()
    }

    private fun setupSourceWordAndLetters() {
        val availableLetters = generateAvailableLetters(sourceWord.lowercase())

        // Clear existing tiles
        val tilesContainer = findViewById<LinearLayout>(R.id.tilesContainer)
        tilesContainer.removeAllViews()
        letterTiles.clear()

        // Create letter tiles from available letters
        availableLetters.forEach { letter ->
            val tile = createLetterTile(letter.toString())
            letterTiles.add(tile)
            tilesContainer.addView(tile)
        }
    }

    private fun generateAvailableLetters(baseWord: String): List<Char> {
        // Use letters from the source word
        val letters = baseWord.toMutableList()

        // Add some common vowels if needed to make sure players can form valid words
        val commonVowels = "aeiou".toList()
        val existingVowels = letters.filter { it in commonVowels }

        // Add extra vowels if we have less than 2 vowels
        if (existingVowels.size < 2) {
            letters.addAll(commonVowels.take(2 - existingVowels.size))
        }

        // Shuffle the letters
        return letters.shuffled()
    }

    private fun resetGame() {
        remainingChances = 3
        clearSlots()
        // Pick a new source word and regenerate letters
        sourceWord = wordList.filter { it.length in 5..6 }.random()
        setupSourceWordAndLetters()
    }

    private fun initializeUI() {
        // Initialize slots for 3-letter words
        val threeLetterSection1 = findViewById<LinearLayout>(R.id.threeLetterSection1)
        val threeLetterSection2 = findViewById<LinearLayout>(R.id.threeLetterSection2)
        val fourLetterSection = findViewById<LinearLayout>(R.id.fourLetterSection)

        // Create slots for words
        repeat(3) {
            val slot1 = createSlot()
            val slot2 = createSlot()
            threeLetterSlots1.add(slot1)
            threeLetterSlots2.add(slot2)
            threeLetterSection1.addView(slot1)
            threeLetterSection2.addView(slot2)
        }

        repeat(4) {
            val slot = createSlot()
            fourLetterSlots.add(slot)
            fourLetterSection.addView(slot)
        }

        // Initialize letter tiles container
        val tilesContainer = findViewById<LinearLayout>(R.id.tilesContainer)
    }

    private fun createSlot(): TextView {
        return TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                100,
                100
            ).apply {
                var margin = 8
            }
            background = getDrawable(R.drawable.slot_background)
            textSize = 24f
            gravity = android.view.Gravity.CENTER
        }
    }

    private fun createLetterTile(letter: String): TextView {
        return TextView(this).apply {
            text = letter.lowercase()  // Ensure letter is lowercase
            textSize = 24f
            gravity = android.view.Gravity.CENTER
            background = getDrawable(R.drawable.tile_background)
            layoutParams = LinearLayout.LayoutParams(
                100,
                100
            ).apply {
                var margin = 8
            }

            // Set up drag listener
            setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Create DragData with isSourceTile set to true
                        val dragData = DragData(view as TextView, true)
                        val shadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(null, shadowBuilder, dragData, 0)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupDragAndDrop() {
        val dragListener = View.OnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    val targetView = view as TextView
                    val dragData = event.localState as DragData
                    val sourceView = dragData.view

                    if (dragData.isSourceTile) {
                        // If dragging from source tiles, just copy the text
                        targetView.text = sourceView.text.toString().lowercase()
                    } else {
                        // If dragging between slots, swap the contents
                        val temp = targetView.text
                        targetView.text = sourceView.text.toString().lowercase()
                        sourceView.text = temp.toString().lowercase()
                    }

                    // Check if section is complete
                    checkSection(view)
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.setBackgroundColor(Color.LTGRAY)
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // Reset background if not completed
                    if (view.background != getDrawable(R.drawable.slot_background)) {
                        view.background = getDrawable(R.drawable.slot_background)
                    }
                    true
                }
                else -> true
            }
        }

        // Apply drag listener to all slots
        threeLetterSlots1.forEach { it.setOnDragListener(dragListener) }
        threeLetterSlots2.forEach { it.setOnDragListener(dragListener) }
        fourLetterSlots.forEach { it.setOnDragListener(dragListener) }

        // Add drag listener to allow dragging between slots
        val slotDragListener = View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if ((view as TextView).text.isNotEmpty()) {
                        val dragData = DragData(view, false)
                        val shadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(null, shadowBuilder, dragData, 0)
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }

        // Apply slot drag listener to all slots
        threeLetterSlots1.forEach { it.setOnTouchListener(slotDragListener) }
        threeLetterSlots2.forEach { it.setOnTouchListener(slotDragListener) }
        fourLetterSlots.forEach { it.setOnTouchListener(slotDragListener) }
    }

    private fun checkSection(view: View) {
        val section = when {
            threeLetterSlots1.contains(view) -> threeLetterSlots1
            threeLetterSlots2.contains(view) -> threeLetterSlots2
            else -> fourLetterSlots
        }

        val word = section.joinToString("") { it.text.toString().lowercase() }

        if (word.length == section.size) {
            if (wordList.contains(word)) {
                // Correct word
                section.forEach { it.setBackgroundColor(Color.GREEN) }
            } else {
                // Wrong word
                section.forEach { it.setBackgroundColor(Color.RED) }
                remainingChances--

                if (remainingChances <= 0) {
                    gameOver()
                } else {
                    Toast.makeText(this, "Wrong word! $remainingChances chances left", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun gameOver() {
        // Show game over dialog with retry button
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("You've run out of chances!")
            .setPositiveButton("Try Again") { _, _ ->
                resetGame()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    private fun clearSlots() {
        threeLetterSlots1.forEach {
            it.text = ""
            it.setBackgroundColor(Color.TRANSPARENT)
        }
        threeLetterSlots2.forEach {
            it.text = ""
            it.setBackgroundColor(Color.TRANSPARENT)
        }
        fourLetterSlots.forEach {
            it.text = ""
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}