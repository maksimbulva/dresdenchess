package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.pieces.IPiece
import ru.maksimbulva.dresdenchess.position.Fen
import ru.maksimbulva.dresdenchess.position.Position

object BoardEncoder : IEncoder {

    override fun encode(position: Position): String {
        val rows = Rows.ROW_8 downTo Rows.ROW_1
        return rows.map { encodeRow(it, position.board) }
            .joinToString(Fen.ROWS_SEPARATOR.toString())
    }

    private fun encodeRow(row: Int, board: Board): String {
        val sb = StringBuilder()
        var emptyCellsCounter = 0
        for (column in Columns.all) {
            val node = board.lookupCell(Cell.encode(row, column))
            if (node != null) {
                if (emptyCellsCounter != 0) {
                    sb.append(emptyCellsCounter)
                    emptyCellsCounter = 0
                }
                sb.append(pieceToChar(node.player, node.piece))
            } else {
                ++emptyCellsCounter
            }
        }
        if (emptyCellsCounter != 0) {
            sb.append(emptyCellsCounter)
        }
        return sb.toString()
    }

    private fun pieceToChar(player: Players, piece: IPiece): Char {
        val char = when (piece.piece) {
            Pieces.PAWN -> 'p'
            Pieces.KNIGHT -> 'n'
            Pieces.BISHOP -> 'b'
            Pieces.ROOK -> 'r'
            Pieces.QUEEN -> 'q'
            Pieces.KING -> 'k'
            else -> throw IllegalStateException()
        }
        return if (player == Players.WHITE) {
            char.toUpperCase()
        } else {
            char
        }
    }
}
