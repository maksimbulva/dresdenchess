package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.moves.MoveTableGenerator

object Knight : PieceJumper() {
    val moveDirs = arrayOf(
        -2 to -1,
        -2 to 1,
        -1 to -2,
        -1 to 2,
        1 to -2,
        1 to 2,
        2 to -1,
        2 to 1
    )

    override val moveDirTable = MoveTableGenerator.generateTable(moveDirs)

    override val piece = Pieces.KNIGHT
}
