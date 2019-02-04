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
            val boardNode = board.lookupCell(destCell)
            if (boardNode == null) {
                moves.add(Move.encode(piece, fromCell, destCell))
            } else if (boardNode.player != playerToMove) {
                moves.add(Move.encodeCapture(piece, fromCell, destCell, boardNode.piece.piece))
            }
        }
    }

    override fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean {
        return moveDirTable[myCell].any { moveDir ->
            myCell + moveDir == targetCell
        }
    }
}
