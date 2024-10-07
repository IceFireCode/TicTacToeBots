package org.example.game

import org.example.game.model.Board
import org.example.game.model.Player

class TicTacToeBots {

    private val board: Board = Board()

    // private val gameUI: GameUI = CliGameUI()

    private var currentPlayer: Player = Player.ONE // TODO allow user to choose which player starts (X or O)

    fun start(){
        while (!board.isFinished()){
            // gameUI.showCurrentPlayer(currentPlayer)
             board.makeMove(currentPlayer)
            // gameUI.showBoard(board)
        }

        // gameUI.showEndResult
    }
}