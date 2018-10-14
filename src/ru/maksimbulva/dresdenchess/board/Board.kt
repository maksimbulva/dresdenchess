package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players

class Board(
        var whiteKingCell: Int,
        var blackKingCell: Int
) {
    private val nodes = Array(64) { Node(it) }

    private var whiteKingNode = nodes[whiteKingCell].setPiece(Players.WHITE, Pieces.KING)
    private var blackKingNode = nodes[blackKingCell].setPiece(Players.BLACK, Pieces.KING)

    val whitePieces: Iterator<Node>
        get() = PlayerPiecesIterator(whiteKingNode)

    val blackPieces: Iterator<Node>
        get() = PlayerPiecesIterator(blackKingNode)

    fun pieces(player: Int) = if (player == Players.WHITE) {
        whitePieces
    } else {
        blackPieces
    }

    fun isEmpty(cell: Int) = !nodes[cell].hasPiece()

    fun updatePieceCell(fromCell: Int, toCell: Int) {
        val fromNode = nodes[fromCell]
        val piece = fromNode.piece
        val toNode = nodes[toCell]
        toNode.clear()
        toNode.player = fromNode.player
        toNode.piece = fromNode.piece
        fromNode.clear()
        if (piece == Pieces.KING) {
            onKingMoved(fromCell, toCell)
        }
    }

    private fun onKingMoved(fromCell: Int, toCell: Int) {
        if (fromCell == whiteKingCell) {
            whiteKingCell = toCell
            whiteKingNode = nodes[whiteKingCell]
        } else {
            blackKingCell = toCell
            blackKingNode = nodes[blackKingCell]
        }
    }

    class Node(val cell: Int) {
        var player = Players.WHITE
        var piece = Pieces.NONE
        var next = NO_NODE
        var prev = NO_NODE
        val isValid = cell >= 0

        fun setPiece(player: Int, piece: Int): Node {
            this.player = player
            this.piece = piece
            return this
        }

        fun clear() {
            piece = Pieces.NONE
            if (next.isValid) {
                next.prev = prev
            }
            if (prev.isValid) {
                prev.next = next
            }
        }

        fun hasPiece() = piece != Pieces.NONE
    }

    private class PlayerPiecesIterator(private var nextNode: Node) : Iterator<Node> {
        override fun hasNext() = nextNode.isValid

        override fun next(): Node {
            val result = nextNode
            nextNode = nextNode.next
            return result
        }
    }

    companion object {
        private val NO_NODE = Node(cell = -1)
    }
}

