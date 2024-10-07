package org.example.game.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `When a new board is created no fields are selected`() {
        // When
        val board = Board()

        // Then
        val expected = listOf(listOf(null, null, null), listOf(null, null, null), listOf(null, null, null))
        assertEquals(expected, board.fields)
    }

    @Test
    fun `Given an initial board is provided, When a new board is created the fields are set to the provided boards fields`() {
        // Given
        val initialBoard = listOf(
            listOf(null, Player.ONE, null),
            listOf(null, Player.TWO, Player.ONE),
            listOf(null, null, Player.TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)

        // Then
        assertEquals(initialBoard, board.fields)
    }
}