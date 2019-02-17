package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.board.Direction
import ru.maksimbulva.dresdenchess.moves.Move
import ru.maksimbulva.dresdenchess.position.Position

abstract class PieceLineAttacker(private val directions: Array<Direction>) : IPiece {

    override val isJumper = false

    override fun generateSemiLegalMoves(position: Position, fromCell: Int, moves: MutableList<Int>) {
        directions.forEach { direction ->
            generateLineMoves(
                position,
                Pieces.BISHOP,
                fromCell,
                direction,
                moves
            )
        }
    }

    override fun isAttacksCell(targetCell: Int, myCell: Int, board: Board): Boolean {
        // TODO
        return false
    }

    private fun generateLineMoves(
        position: Position,
        piece: Int,
        fromCell: Int,
        direction: Direction,
        moves: MutableList<Int>
    ) {
        val board = position.board
        val playerToMove = position.playerToMove
        val moveDelta = direction.moveDelta
        val maxLineLength = direction.maxLineLength(fromCell)
        var curCell = fromCell
        for (i in 0 until maxLineLength) {
            curCell += moveDelta
            val boardNode = board.lookupCell(curCell)
            if (boardNode == null) {
                moves.add(Move.encode(piece, fromCell, curCell))
            } else {
                if (boardNode.player != playerToMove) {
                    moves.add(Move.encodeCapture(piece, fromCell, curCell, boardNode.piece.piece))
                }
                break
            }
        }
    }
}
