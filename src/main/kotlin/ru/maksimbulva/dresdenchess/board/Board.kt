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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other === null) return false
        if (this::class.java != other::class.java) return false

        other as Board
        return cells.asSequence().zip(other.cells.asSequence())
            .all {
                if (it.first === null && it.second === null) return true
                if (it.first === null || it.second === null) return false
                return it.first?.data == it.second?.data
            }
    }

    override fun hashCode() = cells.hashCode()
}
