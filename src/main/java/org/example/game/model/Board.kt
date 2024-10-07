package org.example.game.model

data class Board(
    val initialBoard: Board? = null, // this param allows for setting the start state in tests
) {
    val fields: List<List<Player?>> = initialBoard?.fields ?: listOf(
        listOf(null, null, null),
        listOf(null, null, null),
        listOf(null, null, null),
    )
}
