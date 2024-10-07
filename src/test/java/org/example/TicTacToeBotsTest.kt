package org.example

import org.example.game.TicTacToeBots
import org.example.game.model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TicTacToeBotsTest {
    @Test
    fun `initialize board should create an empty board`() {
        val ticTacToe = TicTacToeBots()
        val board: List<Player?> = ticTacToe.initializeBoard()
        val expectedBoard: List<Player?> = listOf<Player?>(null, null, null, null, null, null, null, null, null)
        assertEquals(expectedBoard, board)
    }
}