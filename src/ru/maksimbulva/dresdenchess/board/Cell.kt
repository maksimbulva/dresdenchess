package ru.maksimbulva.dresdenchess.board

object Cell {
    const val NONE = 127

    const val MOVE_RIGHT = 1
    const val MOVE_LEFT = -MOVE_RIGHT
    const val MOVE_UP = 8
    const val MOVE_DOWN = -MOVE_UP

    const val MOVE_UP_RIGHT = MOVE_UP + MOVE_RIGHT
    const val MOVE_DOWN_RIGHT = MOVE_DOWN + MOVE_RIGHT
    const val MOVE_DOWN_LEFT = MOVE_DOWN + MOVE_LEFT
    const val MOVE_UP_LEFT = MOVE_UP + MOVE_LEFT

    fun row(cell: Int) = cell shr 3
    fun column(cell: Int) = cell and 7

    fun encode(row: Int, column: Int) = (row shl 3) + column
}
