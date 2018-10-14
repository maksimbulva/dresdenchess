package ru.maksimbulva.dresdenchess.moves

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.position.Position

object MovesGenerator {
    fun generateSemiLegalMoves(position: Position): List<Int> {
        val moves = ArrayList<Int>(128)

        val board = position.board
        val pieces = board.pieces(position.playerToMove)
        pieces.forEach {
            val fromCell = it.cell
            when (it.piece) {
                Pieces.KING -> King.generateSemiLegalMoves(position, fromCell, moves)
            }
        }
        return moves
    }
}