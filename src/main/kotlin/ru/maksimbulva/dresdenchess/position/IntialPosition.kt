package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.pieces.*

fun createInitialPosition(): Position {
    // TODO - replace with position set from decoded FEN string
    val result = Position(whiteKingCell = Cells.E1, blackKingCell = Cells.E8)
    val board = result.board
    for (column in Columns.COLUMN_A..Columns.COLUMN_H) {
        board.addPiece(Players.WHITE, WhitePawn, Cell.encode(Rows.ROW_2, column))
    }
    board.addPiece(Players.WHITE, Rook, Cells.A1)
    board.addPiece(Players.WHITE, Knight, Cells.B1)
    board.addPiece(Players.WHITE, Bishop, Cells.C1)
    board.addPiece(Players.WHITE, Queen, Cells.D1)
    board.addPiece(Players.WHITE, Bishop, Cells.F1)
    board.addPiece(Players.WHITE, Knight, Cells.G1)
    board.addPiece(Players.WHITE, Rook, Cells.H1)
    return result
}