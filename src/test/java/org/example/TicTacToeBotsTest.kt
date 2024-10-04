package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TicTacToeBotsTest {
    @Test
    fun `initialize board should create an empty board`() {
        val ticTacToe = TicTacToeBots()
        val board = ticTacToe.initializeBoard()
        val expectedBoard = listOf(null, null, null, null, null, null, null, null, null)
        assertArrayEquals(expectedBoard, board)
    }
}