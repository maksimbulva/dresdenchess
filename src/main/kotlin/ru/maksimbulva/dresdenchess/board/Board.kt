package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.collections.PiecesLinkedList
import ru.maksimbulva.dresdenchess.pieces.King

class Board(
        whiteKingCell: Int,
        blackKingCell: Int
) {
    private val cells = Array<PiecesLinkedList.Node<BoardCell>?>(size = 64) { null }

    private val whitePieces = PiecesLinkedList(BoardCell(Players.WHITE, King, whiteKingCell))

    private val blackPieces = PiecesLinkedList(BoardCell(Players.BLACK, King, blackKingCell))

    init {
        cells[whiteKingCell] = whitePieces.head
        cells[blackKingCell] = blackPieces.head
    }

    fun pieces(player: Players): PiecesLinkedList<BoardCell> {
        return if (player == Players.WHITE) {
            whitePieces
        } else {
            blackPieces
        }
    }

    fun lookupCell(cell: Int) = cells[cell]?.data

    fun isEmpty(cell: Int) = cells[cell] == null

    fun kingCell(player: Players) = pieces(player).head.data.cell

    fun removePieceAt(cell: Int) {
        val node = cells[cell] ?: throw IllegalStateException()
        node.removeFromList()
        cells[cell] = null
    }

    fun addPiece(boardCell: BoardCell) {
        val cell = boardCell.cell
        require(isEmpty(cell))
        assert(boardCell.piece.piece != Pieces.KING)
        cells[cell] = pieces(boardCell.player).add(boardCell)
    }

    fun updatePiece(oldCell: Int, newData: BoardCell) {
        val node = cells[oldCell] ?: throw IllegalArgumentException()
        node.data = newData
        cells[oldCell] = null
        cells[newData.cell] = node
    }
}
