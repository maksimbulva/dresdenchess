package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.collections.PiecesLinkedList
import ru.maksimbulva.dresdenchess.pieces.IPiece
import ru.maksimbulva.dresdenchess.pieces.King

class Board(
        whiteKingCell: Int,
        blackKingCell: Int
) {
    private val cells = Array<PiecesLinkedList.Node?>(size = 64) { null }

    val whitePieces = PiecesLinkedList().apply {
        add(player = Players.WHITE, piece = King, cell = whiteKingCell)
    }

    val blackPieces = PiecesLinkedList().apply {
        add(player = Players.BLACK, piece = King, cell = blackKingCell)
    }

    init {
        cells[whiteKingCell] = whitePieces.head
        cells[blackKingCell] = blackPieces.head
    }

    fun pieces(player: Players): PiecesLinkedList {
        return if (player == Players.WHITE) {
            whitePieces
        } else {
            blackPieces
        }
    }

    fun lookupCell(cell: Int) = cells[cell]

    fun isEmpty(cell: Int) = cells[cell] == null

    fun isNotOccupiedBy(player: Players, cell: Int): Boolean {
        val node = cells[cell]
        return node == null || node.player != player
    }

    fun kingCell(player: Players): Int {
        return pieces(player).head?.cell ?: throw IllegalStateException()
    }

    fun updatePieceCell(fromCell: Int, toCell: Int) {
        val node = cells[fromCell] ?: throw IllegalStateException()
        assert(cells[toCell] == null)
        node.cell = toCell
        cells[fromCell] = null
        cells[toCell] = node
    }

    fun removePieceAt(cell: Int) {
        val node = cells[cell] ?: throw IllegalStateException()
        node.removeFromList()
        cells[cell] = null
    }

    fun addPiece(player: Players, piece: IPiece, cell: Int) {
        require(isEmpty(cell))
        assert(piece.piece != Pieces.KING)
        cells[cell] = pieces(player).add(player, piece, cell)
    }
}
