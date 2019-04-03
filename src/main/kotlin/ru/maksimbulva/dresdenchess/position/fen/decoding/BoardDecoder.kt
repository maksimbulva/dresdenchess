package ru.maksimbulva.dresdenchess.position.fen.decoding

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*
import ru.maksimbulva.dresdenchess.exceptions.InvalidPositionEncodingException
import ru.maksimbulva.dresdenchess.position.Fen.ROWS_SEPARATOR

object BoardDecoder {
    fun decode(encoded: String): Board {
        val boardCells = mutableListOf<BoardCell>()
        var row = Rows.ROW_8
        var column = Columns.COLUMN_A
        encoded.forEach {
            when {
                it == ROWS_SEPARATOR -> {
                    row -= 1
                    column = Columns.COLUMN_A
                }
                it.isDigit() -> column += (it - '0')
                else -> {
                    val (player, piece) = charToPlayerAndPiece(it)
                    val cell = Cell.encodeOrNull(row, column) ?: throw InvalidPositionEncodingException()
                    boardCells.add(BoardCell(player, Pieces.instance(piece, player), cell))
                    ++column
                }
            }
        }
        return BoardFactory.create(boardCells)
    }

    private fun charToPlayerAndPiece(c: Char): Pair<Players, Int>
    {
        val player = if (c.isUpperCase()) {
            Players.WHITE
        } else {
            Players.BLACK
        }
        return player to when (c.toLowerCase()) {
            'p' -> Pieces.PAWN
            'n' -> Pieces.KNIGHT
            'b' -> Pieces.BISHOP
            'r' -> Pieces.ROOK
            'q' -> Pieces.QUEEN
            'k' -> Pieces.KING
            else -> throw InvalidPositionEncodingException()
        }
    }
}