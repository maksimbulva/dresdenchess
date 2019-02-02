package ru.maksimbulva.dresdenchess

// data class Move(val from: Int, val to: Int)

object Move {
    fun encode(piece: Int, from: Int, to: Int) = (piece shl 12) + (from shl 6) + to

    fun piece(move: Int) = (move shr 12)
    fun fromCell(move: Int) = (move shr 6) and 63
    fun destCell(move: Int) = move and 63
}