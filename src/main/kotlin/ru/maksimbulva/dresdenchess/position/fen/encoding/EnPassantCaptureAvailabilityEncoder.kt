package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.pieces.BlackPawn
import ru.maksimbulva.dresdenchess.pieces.WhitePawn
import ru.maksimbulva.dresdenchess.position.Fen
import ru.maksimbulva.dresdenchess.position.Position
import ru.maksimbulva.dresdenchess.position.PositionFlags

object EnPassantCaptureAvailabilityEncoder : IEncoder {
    override fun encode(position: Position): String {
        val flags = position.flags
        return if (PositionFlags.isCanCaptureEnPassant(flags)) {
            val row = if (position.playerToMove == Players.WHITE) {
                WhitePawn.enPassantCaptureRow
            } else {
                BlackPawn.enPassantCaptureRow
            }
            Cell.toString(Cell.encode(row, PositionFlags.enPassantColumn(flags)))
        } else {
            Fen.CannotCaptureEnPassant
        }
    }
}
