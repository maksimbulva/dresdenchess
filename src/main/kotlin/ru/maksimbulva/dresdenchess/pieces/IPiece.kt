package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.position.Position

interface IPiece {
    val piece: Int
    val isJumper: Boolean
    fun generateSemiLegalMoves(position: Position, fromCell: Int, moves: MutableList<Int>)
    fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean
}
