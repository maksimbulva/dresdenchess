package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players

fun Board.isCellAttacked(targetCell: Int, attacker: Int): Boolean {
    // Consider using array here if it does not hurt the performance
    var checkDirDownLeft = false
    var checkDirDown = false
    var checkDirDownRight = false
    var checkDirLeft = false
    var checkDirRight = false
    var checkDirUpLeft = false
    var checkDirUp = false
    var checkDirUpRight = false

    val targetRow = Cell.row(targetCell)
    val targetColumn = Cell.column(targetCell)

    val attackerPieces = pieces(attacker)
    val isAttacked = attackerPieces.any {
        val row = Cell.row(it.cell)
        val column = Cell.column(it.cell)
        val piece = it.piece.piece

        if (piece == Pieces.BISHOP || piece == Pieces.QUEEN) {
            if (row - targetRow == column - targetColumn) {
                checkDirUpRight = checkDirUpRight || (row > targetRow)
                checkDirDownLeft = checkDirDownLeft || (row < targetRow)
            } else if (row - targetRow == targetColumn - column) {
                checkDirUpLeft = checkDirUpLeft || (row > targetRow)
                checkDirDownRight = checkDirDownRight || (row < targetRow)
            }
        }

        if (piece == Pieces.ROOK || piece == Pieces.QUEEN) {
            if (row == targetRow) {
                checkDirUp = checkDirUp || (row > targetRow)
                checkDirDown = checkDirDown || (row < targetRow)
            } else if (column == targetColumn) {
                checkDirLeft = checkDirLeft || (column < targetColumn)
                checkDirRight = checkDirRight || (column > targetColumn)
            }
        }

        when (it.piece.piece) {

            Pieces.PAWN -> {
                Math.abs(column - targetColumn) == 1
                        && ((attacker == Players.WHITE && row + 1 == targetRow)
                        || (attacker == Players.BLACK && row - 1 == targetRow))
            }

            Pieces.KNIGHT -> {
                val absDeltaRow = Math.abs(row - targetRow)
                val absDeltaColumn = Math.abs(column - targetColumn)
                (absDeltaRow == 1 && absDeltaColumn == 2)
                        || (absDeltaRow == 2 && absDeltaColumn == 1)
            }

            Pieces.KING -> {
                Math.abs(row - targetRow) <= 1 && Math.abs(column - targetColumn) <= 1
            }

            else -> {
                // do nothing, these pieces are processed outside of the `when` expression
                false
            }
        }
    }

    return isAttacked
        || (checkDirDownLeft
            && isAttackedFromDir(this, targetCell, attacker, DirectionDownLeft))
        || (checkDirDown
            && isAttackedFromDir(this, targetCell, attacker, DirectionDown))
        || (checkDirDownRight
            && isAttackedFromDir(this, targetCell, attacker, DirectionDownRight))
        || (checkDirLeft
            && isAttackedFromDir(this, targetCell, attacker, DirectionLeft))
        || (checkDirRight
            && isAttackedFromDir(this, targetCell, attacker, DirectionRight))
        || (checkDirUpLeft
            && isAttackedFromDir(this, targetCell, attacker, DirectionUpLeft))
        || (checkDirUp
            && isAttackedFromDir(this, targetCell, attacker, DirectionUp))
        || (checkDirUpRight
            && isAttackedFromDir(this, targetCell, attacker, DirectionUpRight))
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
