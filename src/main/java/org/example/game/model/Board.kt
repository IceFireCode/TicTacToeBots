package org.example.game.model

import org.example.game.model.GameState.Won

data class Board(
    val initialBoard: List<List<Player?>>? = null, // this param allows for setting the start state in tests
) {
    fun getState(): GameState {
        val anyHorizontalWinner = anyHorizontalWinner()
        val anyVerticalWinner = anyVerticalWinner()
        val anyDiagonalWinner = anyDiagonalWinner()

        return when {
            anyHorizontalWinner != null -> Won(byPlayer = anyHorizontalWinner)
            anyVerticalWinner != null -> Won(byPlayer = anyVerticalWinner)
            anyDiagonalWinner != null -> Won(byPlayer = anyDiagonalWinner)
            else -> GameState.Ongoing
        }
    }

    val fields: List<List<Player?>> = initialBoard ?: listOf(
        listOf(null, null, null),
        listOf(null, null, null),
        listOf(null, null, null),
    )

    private fun anyHorizontalWinner(): Player? = fields.findHorizontalWinner()

    private fun anyVerticalWinner(): Player? {
        val mapColumsToRows: List<List<Player?>> = listOf(
            listOf(fields[0][0], fields[1][0], fields[2][0]),
            listOf(fields[0][1], fields[1][1], fields[2][1]),
            listOf(fields[0][2], fields[1][2], fields[2][2]),
        )
        return mapColumsToRows.findHorizontalWinner()
    }

    private fun List<List<Player?>>.findHorizontalWinner() = firstOrNull { row: List<Player?> ->
        val allSelectedByPlayerOne = row.all { it == Player.ONE }
        val allSelectedByPlayerTwo = row.all { it == Player.TWO }
        allSelectedByPlayerOne || allSelectedByPlayerTwo
    }?.first()

    private fun anyDiagonalWinner(): Player? {
        val mapDiagonalsToRows: List<List<Player?>> = listOf(
            listOf(fields[0][0], fields[1][1], fields[2][2]),
            listOf(fields[0][2], fields[1][1], fields[2][0]),
        )
        return mapDiagonalsToRows.findHorizontalWinner()
    }

}
