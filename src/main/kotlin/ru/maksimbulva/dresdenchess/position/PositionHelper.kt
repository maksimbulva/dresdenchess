package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.pieces.IPiece

object PositionHelper {
    fun create(
        white: List<Pair<Int, IPiece>>,
        black: List<Pair<Int, IPiece>>,
        playerToMove: Int,
        isWhiteCanCastleShort: Boolean,
        isWhiteCanCastleLong: Boolean,
        isBlackCanCastleShort: Boolean,
        isBlackCanCastleLong: Boolean,
        enPassantCaptureColumn: Int?,
        halfmoveClock: Int,
        fullmoveCounter: Int
    ): Position {
        // TODO
        val position = Position(
            whiteKingCell = findKingCell(white),
            blackKingCell = findKingCell(black)
        )
        addPieces(position.board, Players.WHITE, white)
        addPieces(position.board, Players.BLACK, black)

        position.isWhiteCanCastleShort = isWhiteCanCastleShort
        position.isWhiteCanCastleLong = isWhiteCanCastleLong
        position.isBlackCanCastleShort = isBlackCanCastleShort
        position.isBlackCanCastleLong = isBlackCanCastleLong

        return position
    }

    private fun findKingCell(pieces: List<Pair<Int, IPiece>>): Int {
        return pieces.find { it.second.piece == Pieces.KING }!!.first
    }

    private fun addPieces(board: Board, player: Int, pieces: List<Pair<Int, IPiece>>) {
        pieces.asSequence()
            .filter { it.second.piece != Pieces.KING }
            .forEach { (cell, piece) -> board.addPiece(player, piece, cell) }
    }
}