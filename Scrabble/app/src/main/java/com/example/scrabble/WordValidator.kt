package com.example.scrabble

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class WordValidator(private val context: Context) {
    private var wordList: Set<String> = setOf()

    init {
        loadWords()
    }

    private fun loadWords() {
        try {
            val inputStream = context.assets.open("words.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            wordList = reader.readLines().toSet()
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isValidWord(word: String): Boolean {
        return word.lowercase() in wordList
    }

    fun getRandomWord(length: Int): String {
        return wordList.filter { it.length == length }.random()
    }

    fun scrambleWord(word: String): String {
        return word.toList().shuffled().joinToString("")
    }
}