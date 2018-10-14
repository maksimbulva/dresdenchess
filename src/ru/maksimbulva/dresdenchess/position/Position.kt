package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board

class Position(whiteKingCell: Int, blackKingCell: Int) {
    val board = Board(whiteKingCell, blackKingCell)

    var isWhiteToMove: Boolean = true
        get() = true

    val playerToMove: Int = Players.WHITE

        /*
        fun playMove(move: Int) {
            val destCell = Move.destCell(move)
            val pieces = if (whiteToMove) _whitePieces else _blackPieces
            pieces.king = PieceOnBoard.updateCell(pieces.king, destCell)
            board[destCell] = _whitePieces.king
            board[Move.fromCell(move)] = 0
            whiteToMove = !whiteToMove
        }

        fun undoMove(move: Int) {
            val fromCell = Move.fromCell(move)
            val pieces = if (whiteToMove) _blackPieces else _whitePieces
            pieces.king = PieceOnBoard.updateCell(pieces.king, fromCell)
            board[fromCell] = _whitePieces.king
            board[Move.destCell(move)] = 0
            whiteToMove = !whiteToMove
        }

        fun addPiece(
                player: Int,
                piece: Int,
                cell: Int
        ) {
            if (board[cell] != 0) {
                throw Exception("ru.maksimbulva.dresdenchess.board.Cell $cell is already occupied\n${toString()}")
            }
        }*/
}