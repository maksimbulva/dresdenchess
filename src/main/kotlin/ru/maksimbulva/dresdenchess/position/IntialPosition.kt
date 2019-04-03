package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*
import ru.maksimbulva.dresdenchess.pieces.*

fun createInitialPosition(): Position {
    // TODO - replace with position set from decoded FEN string
    val result = Position(whiteKingCell = Cells.E1, blackKingCell = Cells.E8)
    val board = result.board
    for (column in Columns.COLUMN_A..Columns.COLUMN_H) {
        board.addPiece(BoardCell(Players.WHITE, WhitePawn, Cell.encode(Rows.ROW_2, column)))
        board.addPiece(BoardCell(Players.BLACK, BlackPawn, Cell.encode(Rows.ROW_7, column)))
    }
    for ((column, piece) in listOf(
        Columns.COLUMN_A to Rook,
        Columns.COLUMN_B to Knight,
        Columns.COLUMN_C to Bishop,
        Columns.COLUMN_D to Queen,
        Columns.COLUMN_F to Bishop,
        Columns.COLUMN_G to Knight,
        Columns.COLUMN_H to Rook
    )) {
        board.addPiece(BoardCell(Players.WHITE, piece, Cell.encode(Rows.ROW_1, column)))
        board.addPiece(BoardCell(Players.BLACK, piece, Cell.encode(Rows.ROW_8, column)))
    }
    return result
}

fun initialPositionBoard(): Board {
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

    return BoardFactory.create(whitePieces + blackPieces + pawns)
}
