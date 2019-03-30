package ru.maksimbulva.dresdenchess.collections

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.pieces.IPiece

class PiecesLinkedList : Iterable<PiecesLinkedList.Node> {
    class Node(
        var player: Players,
        var piece: IPiece,
        var cell: Int,
        var prev: Node?,
        var next: Node?
    ) {
        fun removeFromList() {
            prev?.let { it.next = next }
            next?.let { it.prev = prev }
            prev = null
            next = null
        }

        override fun toString(): String {
            return "$player ${piece.javaClass} at ${Cell.toString(cell)}"
        }
    }

    class Iterator(private var nextNode: Node?) : kotlin.collections.Iterator<Node> {
        override operator fun hasNext() = nextNode != null

        override operator fun next(): Node {
            val result = nextNode
            nextNode = nextNode?.next
            if (result == null) {
                throw IllegalStateException()
            }
            return result
        }
    }

    var head: Node? = null

    override operator fun iterator() = Iterator(head)

    fun add(player: Players, piece: IPiece, cell: Int): Node {
        val head = head
        return if (head == null) {
            val result = Node(player, piece, cell, prev = null, next = null)
            this.head = result
            result
        } else {
            val secondNode = head.next
            val result = Node(player, piece, cell, prev = head, next = secondNode)
            head.next = result
            secondNode?.let { it.prev = result }
            result
        }
    }
}
