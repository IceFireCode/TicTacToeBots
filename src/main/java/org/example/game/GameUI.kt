package org.example.game

import org.example.game.model.Board
import org.example.game.model.Player

interface GameUI {
    fun showCurrentPlayer(player: Player)
    fun showBoard(board: Board)
    fun showEndResult(board: Board)
}

class CliGameUI : GameUI {
    override fun showCurrentPlayer(player: Player) {
        println("Current player: $player (playing with ${getPlayerSign(player)})")
    }

    override fun showBoard(board: Board) {
        board.fields.forEach { row ->
            println("${getPlayerSign(row[0])}|${getPlayerSign(row[1])}|${getPlayerSign(row[2])}")
        }
        println()
    }

    override fun showEndResult(board: Board) {
        println(board.getState())
        println()
    }

    private fun getPlayerSign(player: Player?): String = when (player) {
        Player.ONE -> "X"
        Player.TWO -> "O"
        null -> " "
    }
}