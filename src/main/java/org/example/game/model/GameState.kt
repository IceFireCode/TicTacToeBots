package org.example.game.model

sealed class GameState {
    data class Won(val byPlayer: Player): GameState()
    object Draw : GameState()
    object Ongoing : GameState()
}
