package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players

fun Board.isCellAttacked(targetCell: Int, attacker: Int): Boolean {
    val directionsToCheck = BooleanArray(size = 8)

    val attackerPieces = pieces(attacker)
    val isAttacked = attackerPieces.any { pieceNode ->
        val vec = Vector2d.fromCells(targetCell, pieceNode.cell)
        val piece = pieceNode.piece.piece

        if (piece == Pieces.BISHOP || piece == Pieces.QUEEN) {
            vec.toBishopDirection()?.let { directionsToCheck[it.id] = true }
        }

        if (piece == Pieces.ROOK || piece == Pieces.QUEEN) {
            vec.toRookDirection()?.let { directionsToCheck[it.id] = true }
        }

        when (piece) {

            Pieces.PAWN -> {
                Math.abs(vec.deltaColumn) == 1
                        && ((attacker == Players.WHITE && vec.deltaRow == -1)
                        || (attacker == Players.BLACK && vec.deltaRow == 1))
            }

            Pieces.KNIGHT -> {
                val absDeltaRow = Math.abs(vec.deltaRow)
                val absDeltaColumn = Math.abs(vec.deltaColumn)
                (absDeltaRow == 1 && absDeltaColumn == 2)
                        || (absDeltaRow == 2 && absDeltaColumn == 1)
            }

            Pieces.KING -> {
                Math.abs(vec.deltaRow) <= 1 && Math.abs(vec.deltaColumn) <= 1
            }

            else -> {
                // do nothing, these pieces are processed outside of the `when` expression
                false
            }
        }
    }

    if (isAttacked) {
        return true
    }

    for (i in directionsToCheck.indices) {
        if (directionsToCheck[i] &&
            isAttackedFromDir(this, targetCell, attacker, DIRECTIONS[i])
        ) {
            return true
        }
    }

    return false
}

fun Board.isCellBecameAttacked(targetCell: Int, cellBecameEmpty: Int): Boolean {
    TODO("Not implemented")
}

private fun isAttackedFromDir(
    board: Board,
    targetCell: Int,
    attacker: Int,
    direction: Direction
): Boolean {

    val moveDelta = direction.moveDelta
    val maxLineLenght = direction.maxLineLength(targetCell)
    var curCell = targetCell
    for (i in 0 until maxLineLenght) {
        curCell += moveDelta
        val boardNode = board.lookupCell(curCell)
        if (boardNode != null) {
            val piece = boardNode.piece.piece
            return (boardNode.player == attacker)
                && (piece == direction.piece || piece == Pieces.QUEEN)
        }
    }
    return false
}
