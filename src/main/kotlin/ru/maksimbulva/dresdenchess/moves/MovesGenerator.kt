package ru.maksimbulva.dresdenchess.moves

import org.jetbrains.annotations.TestOnly
import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.board.isCellAttacked
import ru.maksimbulva.dresdenchess.board.isCellBecameAttacked
import ru.maksimbulva.dresdenchess.collections.PiecesLinkedList
import ru.maksimbulva.dresdenchess.position.Position

object MovesGenerator {
    fun generateMoves(position: Position): List<Int> {
        val semiLegalMoves = generateSemiLegalMoves(position)
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
        val moves = MovesGenerator.generateMoves(position)
        var movesCounter = moves.size.toLong()
        if (depthPly > 0) {
            for (move in moves) {
                position.playMove(move)
                movesCounter += countPossibleMoves(position, depthPly - 1)
                position.undoMove()
            }
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
        val pieceMoved = Move.piece(prevMove)
        val prevMoveDest = Move.destCell(prevMove)
        return if (pieceMoved == Pieces.KING) {
            !board.isCellAttacked(prevMoveDest, position.playerToMove)
        } else {
            val kingCell = board.kingCell(position.otherPlayer)
            val prevMoveFrom = Move.fromCell(prevMove)
            !board.isCellBecameAttacked(kingCell, prevMoveFrom)
        }
    }
}