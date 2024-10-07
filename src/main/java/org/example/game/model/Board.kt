package org.example.game.model

import org.example.game.GetRandomMove
import org.example.game.GetRandomMoveImpl
import org.example.game.model.GameState.Won

data class Board(
    val getRandomMove: GetRandomMove = GetRandomMoveImpl(),
    val initialBoard: MutableList<MutableList<Player?>>? = null, // this param allows for setting the start state in tests
) {
    fun getState(): GameState {
        val anyHorizontalWinner = anyHorizontalWinner()
        val anyVerticalWinner = anyVerticalWinner()
        val anyDiagonalWinner = anyDiagonalWinner()

        return when {
            anyHorizontalWinner != null -> Won(byPlayer = anyHorizontalWinner)
            anyVerticalWinner != null -> Won(byPlayer = anyVerticalWinner)
            anyDiagonalWinner != null -> Won(byPlayer = anyDiagonalWinner)
            isItADraw() -> GameState.Draw
            else -> GameState.Ongoing
        }
    }

    fun isFinished(): Boolean = getState() != GameState.Ongoing

    val fields: MutableList<MutableList<Player?>> = initialBoard ?: mutableListOf(
        mutableListOf(null, null, null),
        mutableListOf(null, null, null),
        mutableListOf(null, null, null),
    )

    fun makeMove(player: Player): Board {
        var foundAMove = false
        while (!foundAMove) {
            val potentialMove: Pair<Int, Int> = getRandomMove()
            if (fields[potentialMove.first][potentialMove.second] == null) {
                fields[potentialMove.first][potentialMove.second] = player
                foundAMove = true
            }
        }
        return this
    }

    private fun anyHorizontalWinner(): Player? = fields.findHorizontalWinner()

    private fun anyVerticalWinner(): Player? {
        val mapColumnsToRows: List<List<Player?>> = listOf(
            listOf(fields[0][0], fields[1][0], fields[2][0]),
            listOf(fields[0][1], fields[1][1], fields[2][1]),
            listOf(fields[0][2], fields[1][2], fields[2][2]),
        )
        return mapColumnsToRows.findHorizontalWinner()
    }

    private fun anyDiagonalWinner(): Player? {
        val mapDiagonalsToRows: List<List<Player?>> = listOf(
            listOf(fields[0][0], fields[1][1], fields[2][2]),
            listOf(fields[0][2], fields[1][1], fields[2][0]),
        )
        return mapDiagonalsToRows.findHorizontalWinner()
    }

    private fun isItADraw(): Boolean = fields.all { it.all { it != null } }

    private fun List<List<Player?>>.findHorizontalWinner() = firstOrNull { row: List<Player?> ->
        val allSelectedByPlayerOne = row.all { it == Player.ONE }
        val allSelectedByPlayerTwo = row.all { it == Player.TWO }
        allSelectedByPlayerOne || allSelectedByPlayerTwo
    }?.first()

}
