package com.example.scrabble

data class GameState(
    var remainingChances: Int = 3,
    var score: Int = 0,
    var currentWord: String = "",
    var isGameOver: Boolean = false,
    var completedWords: MutableSet<String> = mutableSetOf()
)