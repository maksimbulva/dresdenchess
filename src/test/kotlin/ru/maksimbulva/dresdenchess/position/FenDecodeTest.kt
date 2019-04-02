package ru.maksimbulva.dresdenchess.position

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*

internal class FenDecodeTest {

    @Test
    fun decodeInitialPosition() {
        val position = Fen.decode("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

        val initialRowPieces = arrayOf(
            Pieces.ROOK,
            Pieces.KNIGHT,
            Pieces.BISHOP,
            Pieces.QUEEN,
            Pieces.KING,
            Pieces.BISHOP,
            Pieces.KNIGHT,
            Pieces.ROOK
        )

        val whitePieces = initialRowPieces.mapIndexed { column, piece ->
            BoardCell(Players.WHITE, Pieces.instance(piece, Players.WHITE), Cell.encode(Rows.ROW_1, column))
        }
        val blackPieces = initialRowPieces.mapIndexed { column, piece ->
            BoardCell(Players.BLACK, Pieces.instance(piece, Players.BLACK), Cell.encode(Rows.ROW_8, column))
        }
        val pawns = (Columns.COLUMN_A..Columns.COLUMN_H).flatMap {
            listOf(
                BoardCell(Players.WHITE, Pieces.instance(Pieces.PAWN, Players.WHITE), Cell.encode(Rows.ROW_2, it)),
                BoardCell(Players.BLACK, Pieces.instance(Pieces.PAWN, Players.BLACK), Cell.encode(Rows.ROW_7, it))
            )
        }

        assertEquals(
            BoardFactory.create(whitePieces + blackPieces + pawns),
            position.board
        )
    }
}