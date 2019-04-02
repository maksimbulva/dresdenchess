package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.exceptions.InvalidPosition

object BoardFactory {

    fun create(boardCells: List<BoardCell>): Board {
        val board = Board(
            findKingCell(boardCells, Players.WHITE).cell,
            findKingCell(boardCells, Players.BLACK).cell
        )

        boardCells.forEach {
            if (it.piece.piece != Pieces.KING) {
                board.addPiece(it)
            }
        }

        return board
    }

    private fun findKingCell(boardCells: List<BoardCell>, player: Players): BoardCell {
        return boardCells.firstOrNull { it.player == player && it.piece.piece == Pieces.KING }
            ?: throw InvalidPosition("Player $player has no king")
    }
}
