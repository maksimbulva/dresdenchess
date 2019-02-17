package ru.maksimbulva.dresdenchess.board

class Vector2d(val deltaRow: Int, val deltaColumn: Int) {

    fun toBishopDirection(): Direction? {
        return if (deltaRow == deltaColumn) {
            when {
                deltaRow > 0 -> DirectionUpRight
                deltaRow < 0 -> DirectionDownLeft
                else -> null
            }
        } else if (deltaRow == -deltaColumn) {
            if (deltaRow > 0) {
                DirectionUpLeft
            } else {
                DirectionDownRight
            }
        } else {
            null
        }
    }

    fun toRookDirection(): Direction? {
        return if (deltaRow == 0) {
            when {
                deltaColumn > 0 -> DirectionRight
                deltaColumn < 0 -> DirectionLeft
                else -> null
            }
        } else if (deltaColumn == 0) {
            if (deltaRow > 0) {
                DirectionUp
            } else {
                DirectionDown
            }
        } else {
            null
        }

    }

    companion object {
        fun fromCells(originCell: Int, destinationCell: Int) = Vector2d(
            deltaRow = Cell.row(destinationCell) - Cell.row(originCell),
            deltaColumn = Cell.column(destinationCell) - Cell.column(originCell)
        )
    }
}
