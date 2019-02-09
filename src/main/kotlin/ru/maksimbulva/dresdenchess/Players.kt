package ru.maksimbulva.dresdenchess

object Players {
    const val WHITE = 0
    const val BLACK = 8

    const val MASK = BLACK or WHITE
}

// Implementation is based on a fact that Players.WHITE is zero
fun otherPlayer(player: Int) = Players.BLACK - player
