package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.*

object Rook : PieceLineAttacker(
    directions = arrayOf(
        DirectionDown,
        DirectionLeft,
        DirectionRight,
        DirectionUp
    )
) {
    override val piece = Pieces.ROOK
}
