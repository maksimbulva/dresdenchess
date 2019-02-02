package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board
import java.util.*

class Position(whiteKingCell: Int, blackKingCell: Int) {
    val board = Board(whiteKingCell, blackKingCell)

    private val movesPlayed = Stack<Int>()

    var _playerToMove = Players.WHITE
    val playerToMove get() = _playerToMove
    val otherPlayer get() = Players.BLACK - _playerToMove

    fun playMove(move: Int) {
        movesPlayed.add(move)
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        board.updatePieceCell(fromCell, toCell)
        flipPlayerToMove()
    }

    fun undoMove() {
        undoMove(movesPlayed.pop())
    }

    private fun undoMove(move: Int) {
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        board.updatePieceCell(toCell, fromCell)
        flipPlayerToMove()
    }

    private fun flipPlayerToMove() {
        _playerToMove = otherPlayer
    }
}