package ru.maksimbulva.dresdenchess.board

object Columns {
    const val COLUMN_A = 0
    const val COLUMN_B = 1
    const val COLUMN_C = 2
    const val COLUMN_D = 3
    const val COLUMN_E = 4
    const val COLUMN_F = 5
    const val COLUMN_G = 6
    const val COLUMN_H = 7

    private val STRINGS = mapOf(
        COLUMN_A to "a",
        COLUMN_B to "b",
        COLUMN_C to "c",
        COLUMN_D to "d",
        COLUMN_E to "e",
        COLUMN_F to "f",
        COLUMN_G to "g",
        COLUMN_H to "h"
    )

    fun toString(column: Int) = STRINGS.getValue(column)
}
