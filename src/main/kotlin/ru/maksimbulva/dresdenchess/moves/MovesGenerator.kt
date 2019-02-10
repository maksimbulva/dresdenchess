package ru.maksimbulva.dresdenchess.moves

import org.jetbrains.annotations.TestOnly
import ru.maksimbulva.dresdenchess.board.isCellAttacked
import ru.maksimbulva.dresdenchess.position.Position

object MovesGenerator {
    fun generateMoves(position: Position): List<Int> {
        val semiLegalMoves = generateSemiLegalMoves(position)
        // TODO - remove me
//        val strs = semiLegalMoves.map { Cell.toString(Move.fromCell(it)) + "-" + Cell.toString(Move.destCell(it)) }
        // TODO - consider returning a sequence
        return semiLegalMoves.filter { move ->
            position.playMove(move)
            val result = isValidPosition(position, move)
            position.undoMove()
            result
        }
    }

    @TestOnly
    fun countPossibleMoves(position: Position, depthPly: Int): Long {
        if (depthPly == 0) return 1
        val moves = MovesGenerator.generateMoves(position)
        var movesCounter: Long = 0
        if (depthPly > 1) {
            for (move in moves) {
                position.playMove(move)
                movesCounter += countPossibleMoves(position, depthPly - 1)
                position.undoMove()
            }
        } else {
            movesCounter = moves.size.toLong()
        }
        return movesCounter
    }

    private fun generateSemiLegalMoves(position: Position): List<Int> {
        val moves = ArrayList<Int>(128)

        val board = position.board
        val pieces = board.pieces(position.playerToMove)
        pieces.forEach {
            it.piece.generateSemiLegalMoves(position, it.cell, moves)
        }
        return moves
    }

    private fun isValidPosition(position: Position, prevMove: Int): Boolean {
        val board = position.board
        val kingCell = position.board.kingCell(position.otherPlayer)
        // TODO - optimize me, use isCellBecameAttacked() when sufficient
        return !board.isCellAttacked(kingCell, position.playerToMove)
    }
}
