package org.example.game

import org.example.game.model.Board

interface GameUI {
    fun showEndResult(board: Board)
}

class CliGameUI : GameUI {
    override fun showEndResult(board: Board) {
        println(board.getState())
    }
}