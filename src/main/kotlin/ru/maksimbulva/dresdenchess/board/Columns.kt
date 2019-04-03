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

    val all = COLUMN_A..COLUMN_H

    fun toChar(column: Int) = 'a' + column

    fun fromChar(c: Char): Int? {
        val lower = c.toLowerCase()
        return if (lower in 'a'..'h') {
            lower - 'a'
        } else {
            null
        }
    }
}
