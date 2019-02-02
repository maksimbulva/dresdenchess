package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.position.Position

abstract class PieceJumper : IPiece {
    protected abstract val moveDirTable: Array<IntArray>

    override val isJumper = true

    override fun generateSemiLegalMoves(position: Position, fromCell: Int, moves: MutableList<Int>) {
        val playerToMove = position.playerToMove
        val board = position.board
        for (moveDir in moveDirTable[fromCell]) {
            val destCell = fromCell + moveDir
            if (board.isNotOccupiedBy(playerToMove, destCell)) {
                moves.add(Move.encode(piece, fromCell, destCell))
            }
        }
    }

    override fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean {
        return moveDirTable[myCell].any { moveDir ->
            myCell + moveDir == targetCell
        }
    }
}
