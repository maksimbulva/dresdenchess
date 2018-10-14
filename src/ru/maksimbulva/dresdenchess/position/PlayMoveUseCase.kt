package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Move

class PlayMoveUseCase(val position: Position) {
    private val board = position.board

    fun playMove(move: Int) {
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        board.updatePieceCell(fromCell, toCell)
    }

    fun undoMove(move: Int) {
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        board.updatePieceCell(toCell, fromCell)
    }
}
