package ru.maksimbulva.dresdenchess

import ru.maksimbulva.dresdenchess.pieces.*

object Pieces {
    const val NONE = 0
    const val PAWN = 1
    const val KNIGHT = 2
    const val BISHOP = 3
    const val ROOK = 4
    const val QUEEN = 5
    const val KING = 6

    const val MASK = 7

    fun instance(piece: Int, player: Players): IPiece {
        return when (piece) {
            PAWN -> if (player == Players.WHITE) WhitePawn else BlackPawn
            KNIGHT -> Knight
            BISHOP -> Bishop
            ROOK -> Rook
            QUEEN -> Queen
            KING -> King
            else -> throw IllegalStateException()
        }
    }
}
