package ru.maksimbulva.dresdenchess

// data class Move(val from: Int, val to: Int)

object Move {
    fun encode(from: Int, to: Int) = (from shl 6) + to

    fun fromCell(move: Int) = (move shr 6) and 63
    fun destCell(move: Int) = move and 63
}