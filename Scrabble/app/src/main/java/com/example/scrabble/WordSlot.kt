package com.example.scrabble

data class WordSlot(
    val length: Int,
    var currentLetters: MutableList<String> = MutableList(length) { "" },
    var isComplete: Boolean = false,
    var isCorrect: Boolean = false
)