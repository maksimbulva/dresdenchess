package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.*

object Bishop : PieceLineAttacker(
    directions = arrayOf(
        DirectionDownLeft,
        DirectionDownRight,
        DirectionUpLeft,
        DirectionUpRight
    )
) {
    override val piece = Pieces.BISHOP
}
