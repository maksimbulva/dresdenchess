package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.moves.MoveTableGenerator

object Knight : PieceJumper(
    moveDirTable = MoveTableGenerator.generateTable(
        arrayOf(
            -2 to -1,
            -2 to 1,
            -1 to -2,
            -1 to 2,
            1 to -2,
            1 to 2,
            2 to -1,
            2 to 1
        )
    )
) {
    override val piece = Pieces.KNIGHT
}
