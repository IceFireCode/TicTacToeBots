package org.example.game.model

import org.example.game.FakeGetRandomMove
import org.example.game.model.Player.ONE
import org.example.game.model.Player.TWO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.exp

class BoardTest {

    @Test
    fun `When a new board is created no fields are selected`() {
        // When
        val board = Board()

        // Then
        val expected = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(null, null, null),
            mutableListOf(null, null, null),
        )
        assertEquals(expected, board.fields)
    }

    @Test
    fun `Given an initial board is provided, When a new board is created the fields are set to the provided boards fields`() {
        // Given
        val initialBoard = mutableListOf(
            mutableListOf(null, ONE, null),
            mutableListOf(null, TWO, ONE),
            mutableListOf(null, null, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)

        // Then
        assertEquals(initialBoard, board.fields)
    }

    @Test
    fun `Given a board state in which no player has won yet, When game state is requested, 'Ongoing' is returned`() {
        // Given
        val initialBoard = mutableListOf(
            mutableListOf(ONE, ONE, null),
            mutableListOf(TWO, null, TWO),
            mutableListOf(ONE, null, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Ongoing, result)
        assertEquals(false, board.isFinished())
    }

    @Test
    fun `Given a board state in which player two has selected all fields in a row, When game state is requested, a win by player 2 is returned`() {
        // Given
        val initialBoard: MutableList<MutableList<Player?>> = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(TWO, TWO, TWO), // the entire row is selected by player 2
            mutableListOf(null, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = TWO), result)
        assertEquals(true, board.isFinished())
    }

    @Test
    fun `Given a board state in which player one has selected all fields in a column, When game state is requested, a win by player 1 is returned`() {
        // Given
        // all fields in the first colum are selected by player 1
        val initialBoard = mutableListOf(
            mutableListOf(ONE, null, null),
            mutableListOf(ONE, null, null),
            mutableListOf(ONE, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = ONE), result)
        assertEquals(true, board.isFinished())
    }

    @Test
    fun `Given a board state in which all fields are selected but no player won, When game state is requested, a draw is returned`() {
        // Given
        val initialBoard: MutableList<MutableList<Player?>> = mutableListOf(
            mutableListOf(ONE, TWO, ONE),
            mutableListOf(TWO, TWO, ONE),
            mutableListOf(TWO, ONE, TWO),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Draw, result)
        assertEquals(true, board.isFinished())
    }

    @Test
    fun `Given a board state in which player one has selected all fields in the first diagonal, When game state is requested, a win by player 1 is returned`() {
        // Given
        // all fields in the first diagonal are selected by player 1
        val initialBoard = mutableListOf(
            mutableListOf(ONE, null, null),
            mutableListOf(null, ONE, null),
            mutableListOf(null, null, ONE),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = ONE), result)
        assertEquals(true, board.isFinished())
    }

    @Test
    fun `Given a board state in which player two has selected all fields in the second diagonal, When game state is requested, a win by player 2 is returned`() {
        // Given
        // all fields in the first diagonal are selected by player 1
        val initialBoard = mutableListOf(
            mutableListOf(null, null, TWO),
            mutableListOf(null, TWO, null),
            mutableListOf(TWO, null, null),
        )

        // When
        val board = Board(initialBoard = initialBoard)
        val result = board.getState()

        // Then
        assertEquals(GameState.Won(byPlayer = TWO), result)
        assertEquals(true, board.isFinished())
    }

    @Test
    fun `Given a random move that can be executed, When a move needs to be made, the updated Board is returned`() {
        // Given
        val randomMove = 1 to 1
        val getRandomMove = FakeGetRandomMove(values = listOf(randomMove))
        val initialBoard: MutableList<MutableList<Player?>> = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(null, null, null),
            mutableListOf(null, null, null),
        )

        // When
        val board = Board(getRandomMove = getRandomMove, initialBoard = initialBoard)
        val result = board.makeMove(TWO).fields

        // Then
        val expected = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(null, TWO, null),
            mutableListOf(null, null, null),
        )
        assertEquals(expected, result)
    }

    @Test
    fun `Given a random move that cannot be executed, but the second random move can, When a move needs to be made, the updated Board is returned`() {
        // Given
        val randomMove1 = 1 to 1
        val randomMove2 = 2 to 2
        val getRandomMove = FakeGetRandomMove(values = listOf(randomMove1, randomMove2))
        val initialBoard: MutableList<MutableList<Player?>> = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(null, ONE, null),
            mutableListOf(null, null, null),
        )

        // When
        val board = Board(getRandomMove = getRandomMove, initialBoard = initialBoard)
        val result = board.makeMove(TWO).fields

        // Then
        val expected = mutableListOf(
            mutableListOf(null, null, null),
            mutableListOf(null, ONE, null),
            mutableListOf(null, null, TWO),
        )
        assertEquals(expected, result)
    }
}