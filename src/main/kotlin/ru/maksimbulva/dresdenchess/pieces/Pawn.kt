package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.position.Position

abstract class Pawn(
    val moveRowDelta: Int,
    val doubleStepMoveRow: Int,
    val enPassantCaptureRow: Int
) : IPiece {

    override val piece = Pieces.PAWN

    override val isJumper = true

    // TODO - consider promotions
    override fun generateSemiLegalMoves(
        position: Position,
        fromCell: Int,
        moves: MutableList<Int>
    ) {
        val fromRow = Cell.row(fromCell)
        val fromColumn = Cell.column(fromCell)

        val board = position.board
        val stepForwardCell = Cell.encode(fromRow + moveRowDelta, fromColumn)
        if (board.isEmpty(stepForwardCell)) {
            moves.add(Move.encode(Pieces.PAWN, fromCell, stepForwardCell))

            if (fromRow == doubleStepMoveRow) {
                val doubleStepForwardCell = Cell.encode(fromRow + 2 * moveRowDelta, fromColumn)
                if (board.isEmpty(doubleStepForwardCell)) {
                    moves.add(Move.encode(Pieces.PAWN, fromCell, doubleStepForwardCell))
                }
            }
        }

        if (fromColumn > Columns.COLUMN_A) {
            val destCell = Cell.encode(fromRow + moveRowDelta, fromColumn - 1)
            generateCapture(position, fromCell, destCell, moves)
        }
        if (fromColumn < Columns.COLUMN_H) {
            val destCell = Cell.encode(fromRow + moveRowDelta, fromColumn + 1)
            generateCapture(position, fromCell, destCell, moves)
        }

        if (fromRow == enPassantCaptureRow && position.isCanCaptureEnPassant) {
            val enPassantCaptureColumn = position.enPassantCaptureColumn
            if (Math.abs(enPassantCaptureColumn - fromColumn) == 1) {
                val destCell = Cell.encode(fromRow + moveRowDelta, enPassantCaptureColumn)
                moves.add(Move.encodeEnPassantCapture(fromCell, destCell))
            }
        }
    }

    override fun isAttacksCell(
        targetCell: Int,
        myCell: Int,
        board: Board
    ): Boolean {
        val myRow = Cell.row(myCell)
        val targetRow = Cell.row(targetCell)
        if (myRow + moveRowDelta != targetRow) return false

        val myColumn = Cell.column(myCell)
        val targetColumn = Cell.column(targetCell)
        return myColumn - 1 == targetColumn || myColumn + 1 == targetColumn
    }

    private fun generateCapture(
        position: Position,
        fromCell: Int,
        destCell: Int,
        moves: MutableList<Int>
    ) {
        val boardNode = position.board.lookupCell(destCell)
        if (boardNode != null && boardNode.player != position.playerToMove) {
            moves.add(Move.encodeCapture(Pieces.PAWN, fromCell, destCell, boardNode.piece.piece))
        }
    }
}