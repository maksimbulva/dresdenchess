package ru.maksimbulva.dresdenchess.position

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*
import ru.maksimbulva.dresdenchess.pieces.*

internal class FenEncodeTest {

    @Test
    fun initialPositionEncoding() {
        val position = PositionHelper.create(
            board = initialPositionBoard(),
            playerToMove = Players.WHITE,
            isWhiteCanCastleShort = true,
            isWhiteCanCastleLong = true,
            isBlackCanCastleShort = true,
            isBlackCanCastleLong = true,
            enPassantCaptureColumn = null,
            halfmoveClock = 0,
            fullmoveCounter = 1
        )

        val encoded = Fen.encode(position, Fen.MoveCounterEncoding.UseActualValue)

        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", encoded)
    }
}