package org.example

import org.example.game.TicTacToeBots
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TicTacToeBotsTest {
    @Test
    fun `initialize board should create an empty board`() {
        val ticTacToe = TicTacToeBots()
        val board: List<String?> = ticTacToe.initializeBoard()
        val expectedBoard: List<String?> = listOf<String?>(null, null, null, null, null, null, null, null, null)
        assertEquals(expectedBoard, board)
    }
}