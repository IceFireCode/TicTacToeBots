package org.example.game

import org.example.game.model.Board
import org.example.game.model.Player

class TicTacToeBots(
    private val gameUI: GameUI = CliGameUI()
) {

    private val board: Board = Board()

    private var currentPlayer: Player = Player.ONE // TODO allow user to choose which player starts (X or O)

    fun start() {
        while (!board.isFinished()) {
             gameUI.showCurrentPlayer(currentPlayer)
            board.makeMove(currentPlayer)
             gameUI.showBoard(board)
            currentPlayer = swapPlayer(currentPlayer = currentPlayer)
        }

        gameUI.showEndResult(board = board)
    }

    // TODO @VisibleForTesting and make private
    fun swapPlayer(currentPlayer: Player): Player = if (currentPlayer == Player.ONE) Player.TWO else Player.ONE
}