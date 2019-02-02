package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.position.Position

object WhitePawn : IPiece {
    override val piece = Pieces.PAWN

    override val isJumper = true

    // TODO - consider promotions and en passant captures
    override fun generateSemiLegalMoves(position: Position, fromCell: Int, moves: MutableList<Int>) {
        val fromRow = Cell.row(fromCell)
        val fromColumn = Cell.column(fromCell)

        val board = position.board
        val stepForwardCell = Cell.encode(fromRow + 1, fromColumn)
        if (board.isEmpty(stepForwardCell)) {
            moves.add(Move.encode(Pieces.PAWN, fromCell, stepForwardCell))

            if (fromRow == Rows.ROW_2) {
                val doubleStepForwardCell = Cell.encode(fromRow + 2, fromColumn)
                if (board.isEmpty(doubleStepForwardCell)) {
                    moves.add(Move.encode(Pieces.PAWN, fromCell, doubleStepForwardCell))
                }
            }
        }
    }

    override fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean {
        val myRow = Cell.row(myCell)
        val targetRow = Cell.row(targetCell)
        if (myRow + 1 != targetRow) return false

        val myColumn = Cell.column(myCell)
        val targetColumn = Cell.column(targetCell)
        return myColumn - 1 == targetColumn || myColumn + 1 == targetColumn
    }
}
