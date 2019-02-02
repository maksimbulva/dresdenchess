package ru.maksimbulva.dresdenchess.board

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

    fun pieces(player: Int): PiecesLinkedList {
        return if (player == Players.WHITE) {
            whitePieces
        } else {
            blackPieces
        }
    }

    fun isEmpty(cell: Int) = cells[cell] == null

    fun isNotOccupiedBy(player: Int, cell: Int): Boolean {
        val node = cells[cell]
        return node == null || node.player != player
    }

    fun kingCell(player: Int): Int {
        return pieces(player).head?.cell ?: throw IllegalStateException()
    }

    fun updatePieceCell(fromCell: Int, toCell: Int) {
        val node = cells[fromCell]
                ?: throw IllegalStateException()
        node.cell = toCell
        cells[fromCell] = null
        cells[toCell] = node
    }

    fun addPiece(player: Int, piece: IPiece, cell: Int) {
        require(isEmpty(cell))
        cells[cell] = pieces(player).add(player, piece, cell)
    }
}
