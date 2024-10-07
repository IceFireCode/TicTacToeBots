package org.example.game.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest {

    private val allFieldsNotSelected = listOf(listOf(null, null, null), listOf(null, null, null), listOf(null, null, null))

    @Test
    fun `Given no initial board is provided, When a new board is created no fields are selected`() {
        val board = Board()
        val expectedFields: List<List<Player?>> = allFieldsNotSelected
        assertEquals(expectedFields, board.fields)
    }
}