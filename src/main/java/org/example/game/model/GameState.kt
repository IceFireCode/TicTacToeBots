package org.example.game.model

sealed class GameState {
    data class Won(val byPlayer: Player): GameState()
    data object Draw : GameState()
    data object Ongoing : GameState()
}
