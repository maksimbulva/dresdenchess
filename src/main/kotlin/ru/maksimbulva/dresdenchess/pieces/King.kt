package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.moves.MoveTableGenerator

object King : PieceJumper() {
    override val moveDirTable = MoveTableGenerator.generateTable(arrayOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1
    ))

    override val piece = Pieces.KING
}
