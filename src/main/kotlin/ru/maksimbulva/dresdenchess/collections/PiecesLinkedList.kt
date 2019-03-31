package ru.maksimbulva.dresdenchess.collections

class PiecesLinkedList<T>(headData: T) {
    class Node<T>(
        var data: T,
        var prev: Node<T>? = null,
        var next: Node<T>? = null
    ) {
        fun removeFromList() {
            prev?.let { it.next = next }
            next?.let { it.prev = prev }
            prev = null
            next = null
        }

        fun insertAfter(prevNode: Node<T>) {
            require(prev == null && next == null)
            prev = prevNode
            next = prevNode.next
            next?.prev = this
            prevNode.next = this
        }
    }

    class Iterator<T>(private var nextNode: Node<T>?) : kotlin.collections.Iterator<T> {
        override operator fun hasNext() = nextNode != null

        override operator fun next(): T {
            val result = nextNode ?: throw NoSuchElementException()
            nextNode = nextNode?.next
            return result.data
        }
    }

    val head = Node(headData)

    val elements = Sequence{ Iterator(head) }

    fun add(data: T): Node<T> {
        return Node(data).apply {
            insertAfter(head)
        }
    }
}
