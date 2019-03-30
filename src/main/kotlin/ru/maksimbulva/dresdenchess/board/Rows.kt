package ru.maksimbulva.dresdenchess.board

object Rows {
    const val ROW_1 = 0
    const val ROW_2 = 1
    const val ROW_3 = 2
    const val ROW_4 = 3
    const val ROW_5 = 4
    const val ROW_6 = 5
    const val ROW_7 = 6
    const val ROW_8 = 7

    fun toChar(row: Int) = '1' + row

    fun fromChar(c: Char): Int? {
        val lower = c.toLowerCase()
        return if (lower in '1'..'8') {
            lower - '1'
        } else {
            return null
        }
    }
}
