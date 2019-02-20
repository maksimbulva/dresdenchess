package ru.maksimbulva.dresdenchess.position

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.pieces.*

internal class FenEncodeTest {

    @Test
    fun initialPositionEncoding() {
        val position = PositionHelper.create(
            white = listOf(
                Cells.E1 to King,
                Cells.D1 to Queen,
                Cells.A1 to Rook,
                Cells.H1 to Rook,
                Cells.C1 to Bishop,
                Cells.F1 to Bishop,
                Cells.B1 to Knight,
                Cells.G1 to Knight
            ) + (Columns.COLUMN_A..Columns.COLUMN_H).map {
                Cell.encode(Rows.ROW_2, it) to WhitePawn
            },
            black = listOf(
                Cells.E8 to King,
                Cells.D8 to Queen,
                Cells.A8 to Rook,
                Cells.H8 to Rook,
                Cells.C8 to Bishop,
                Cells.F8 to Bishop,
                Cells.B8 to Knight,
                Cells.G8 to Knight
            ) + (Columns.COLUMN_A..Columns.COLUMN_H).map {
                Cell.encode(Rows.ROW_7, it) to BlackPawn
            },
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