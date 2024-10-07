package org.example.game.model

import org.example.game.model.Player.ONE
import org.example.game.model.Player.TWO
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
            listOf(null, ONE, null),
            listOf(null, TWO, ONE),
            listOf(null, null, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)

        // Then
        assertEquals(initialBoard, board.fields)
    }

    @Test
    fun `Given a board state in which no player has won yet, When game state is requested, 'Ongoing' is returned`(){
        // Given
        val initialBoard = listOf(
            listOf(ONE, ONE, null),
            listOf(TWO, null, TWO),
            listOf(ONE, null, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Ongoing, result)
    }

    @Test
    fun `Given a board state in which player two has selected all fields in a row, When game state is requested, a win by player 2 is returned`(){
        // Given
        val initialBoard = listOf(
            listOf(null, null, null),
            listOf(TWO, TWO, TWO), // the entire row is selected by player 2
            listOf(null, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = TWO), result)
    }

    @Test
    fun `Given a board state in which player one has selected all fields in a column, When game state is requested, a win by player 1 is returned`(){
        // Given
        // all fields in the first colum are selected by player 1
        val initialBoard = listOf(
            listOf(ONE, null, null),
            listOf(ONE, null, null),
            listOf(ONE, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = ONE), result)
    }

    @Test
    fun `Given a board state in which all fields are selected but no player won, When game state is requested, a draw is returned`(){
        // Given
        val initialBoard = listOf(
            listOf(ONE, TWO, ONE),
            listOf(TWO, TWO, ONE),
            listOf(TWO, ONE, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Draw, result)
    }

    @Test
    fun `Given a board state in which player one has selected all fields in the first diagonal, When game state is requested, a win by player 1 is returned`(){
        // Given
        // all fields in the first diagonal are selected by player 1
        val initialBoard = listOf(
            listOf(ONE, null, null),
            listOf(null, ONE, null),
            listOf(null, null, ONE),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = ONE), result)
    }

    @Test
    fun `Given a board state in which player two has selected all fields in the second diagonal, When game state is requested, a win by player 2 is returned`(){
        // Given
        // all fields in the first diagonal are selected by player 1
        val initialBoard = listOf(
            listOf(null, null, TWO),
            listOf(null, TWO, null),
            listOf(TWO, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = TWO), result)
    }
}