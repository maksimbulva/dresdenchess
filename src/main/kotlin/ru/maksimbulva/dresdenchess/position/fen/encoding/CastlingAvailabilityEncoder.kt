package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.position.Fen
import ru.maksimbulva.dresdenchess.position.Position

object CastlingAvailabilityEncoder : IEncoder {
    override fun encode(position: Position): String {
        val availableCastlings = listOf(
            position.isWhiteCanCastleShort to Fen.FEN_WHITE_CAN_CASTLE_SHORT,
            position.isWhiteCanCastleLong to Fen.FEN_WHITE_CAN_CASTLE_LONG,
            position.isBlackCanCastleShort to Fen.FEN_BLACK_CAN_CASTLE_SHORT,
            position.isBlackCanCastleLong to Fen.FEN_BLACK_CAN_CASTLE_LONG
        )
            .filter { it.first }

        return if (availableCastlings.isEmpty()) {
            Fen.FEN_NO_ONE_CAN_CASTLE.toString()
        } else {
            availableCastlings.map { it.second }.joinToString(separator = "")
        }

    }
}