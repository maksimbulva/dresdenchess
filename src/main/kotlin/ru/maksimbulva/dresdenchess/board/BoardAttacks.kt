package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.collections.PiecesLinkedList

fun Board.isCellAttacked(targetCell: Int, attacker: Int): Boolean {
    return pieces(attacker).any { isCellAttacked(targetCell, it) }
}

fun Board.isCellAttacked(targetCell: Int, attacker: PiecesLinkedList.Node): Boolean {
    val attackerCell = attacker.cell
    return if (attacker.piece.isJumper) {
        attacker.piece.isAttacksCell(targetCell, attackerCell, this)
    } else {
        throw NotImplementedError()
    }
}

fun Board.isCellBecameAttacked(targetCell: Int, cellBecameEmpty: Int): Boolean {
    // TODO - implement me
    return false
}
