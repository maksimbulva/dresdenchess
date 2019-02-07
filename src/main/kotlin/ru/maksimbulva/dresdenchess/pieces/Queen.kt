package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.*

object Queen : PieceLineAttacker(
    directions = arrayOf(
        DirectionDownLeft,
        DirectionDown,
        DirectionDownRight,
        DirectionLeft,
        DirectionRight,
        DirectionUpLeft,
        DirectionUp,
        DirectionUpRight
    )
) {
    override val piece = Pieces.QUEEN
}
