package ru.maksimbulva.dresdenchess.board

class BoardUpdateUseCase(val board: Board) {

    fun move(fromCell: Int, toCell: Int) {
        val oldData = board.lookupCell(fromCell) ?: throw IllegalStateException()
        board.updatePiece(fromCell, oldData.copy(cell = toCell))
    }
}
