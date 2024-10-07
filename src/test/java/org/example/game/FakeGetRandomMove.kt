package org.example.game

class FakeGetRandomMove(val values: List<Pair<Int, Int>>): GetRandomMove {
    private var counter = 0

    override operator fun invoke(): Pair<Int, Int> {
        val result = values[counter]
        counter++
        return result
    }
}