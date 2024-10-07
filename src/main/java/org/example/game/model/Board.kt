package org.example.game.model

import org.example.game.model.GameState.Won

data class Board(
    val initialBoard: List<List<Player?>>? = null, // this param allows for setting the start state in tests
) {
    fun getState(): GameState {
        val anyHorizontalWinner = anyHorizontalWinner()

        return when {
            anyHorizontalWinner != null -> Won(byPlayer = anyHorizontalWinner)
            else -> GameState.Ongoing
        }
    }

    val fields: List<List<Player?>> = initialBoard ?: listOf(
        listOf(null, null, null),
        listOf(null, null, null),
        listOf(null, null, null),
    )

    private fun anyHorizontalWinner(): Player? =
        fields.firstOrNull { row: List<Player?> ->
            val allSelectedByPlayerOne = row.all { it == Player.ONE }
            val allSelectedByPlayerTwo = row.all { it == Player.TWO }
            allSelectedByPlayerOne || allSelectedByPlayerTwo
        }?.first()

}
