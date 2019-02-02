package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.position.Position

object Rook : IPiece {
    override val piece = Pieces.BISHOP

    override val isJumper = false

    override fun generateSemiLegalMoves(position: Position, fromCell: Int, moves: MutableList<Int>) {
        // TODO - implement me
    }

    override fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean {
        // TODO - implement me
        return false
    }
}
