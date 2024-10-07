package org.example.game.model

data class Board(
    val initialBoard: List<List<Player?>>? = null, // this param allows for setting the start state in tests
) {
    fun getState(): GameState = GameState.Ongoing

    val fields: List<List<Player?>> = initialBoard ?: listOf(
        listOf(null, null, null),
        listOf(null, null, null),
        listOf(null, null, null),
    )
}
