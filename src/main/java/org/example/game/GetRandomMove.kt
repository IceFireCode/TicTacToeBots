package org.example.game

import kotlin.random.Random

interface GetRandomMove {
    operator fun invoke(): Pair<Int, Int>
}

class GetRandomMoveImpl: GetRandomMove {
    override operator fun invoke(): Pair<Int, Int> = Random.nextInt(0, 9) to Random.nextInt(0, 9)
}