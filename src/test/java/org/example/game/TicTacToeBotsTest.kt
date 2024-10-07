package org.example.game

import org.example.game.model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TicTacToeBotsTest {

    @Test
    fun `Given player one is the current player, swapPlayer returns player two`(){
        // Given
        val currentPlayer = Player.ONE
        val ticTacToeBots = TicTacToeBots()

        // When
        val result = ticTacToeBots.swapPlayer(currentPlayer)

        // Then
        assertEquals(Player.TWO, result)
    }

    @Test
    fun `Given player two is the current player, swapPlayer returns player one`(){
        // Given
        val currentPlayer = Player.TWO
        val ticTacToeBots = TicTacToeBots()

        // When
        val result = ticTacToeBots.swapPlayer(currentPlayer)

        // Then
        assertEquals(Player.ONE, result)
    }
}