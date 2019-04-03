package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board

object PositionHelper {
    fun create(
        board: Board,
        playerToMove: Players,
        isWhiteCanCastleShort: Boolean,
        isWhiteCanCastleLong: Boolean,
        isBlackCanCastleShort: Boolean,
        isBlackCanCastleLong: Boolean,
        enPassantCaptureColumn: Int?,
        halfmoveClock: Int,
        fullmoveCounter: Int
    ): Position {
        // TODO
        val position = Position(board)

        position.isWhiteCanCastleShort = isWhiteCanCastleShort
        position.isWhiteCanCastleLong = isWhiteCanCastleLong
        position.isBlackCanCastleShort = isBlackCanCastleShort
        position.isBlackCanCastleLong = isBlackCanCastleLong

        return position
    }
}
