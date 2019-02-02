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

    private val STRINGS = mapOf(
        ROW_1 to "1",
        ROW_2 to "2",
        ROW_3 to "3",
        ROW_4 to "4",
        ROW_5 to "5",
        ROW_6 to "6",
        ROW_7 to "7",
        ROW_8 to "8"
    )

    fun toString(row: Int): String = STRINGS.getValue(row)
}
