package ru.maksimbulva.dresdenchess.board

object Cells {
    const val A1 = 0
    const val B1 = 1
    const val C1 = 2
    const val D1 = 3
    const val E1 = 4
    const val F1 = 5
    const val G1 = 6
    const val H1 = 7
    const val E8 = (Rows.ROW_8 shl 3) + Columns.COLUMN_E
    const val A8 = 56
    const val H8 = 63

    fun ofRow(row: Int) = IntRange(Cell.encode(row, Columns.COLUMN_A), Cell.encode(row, Columns.COLUMN_H))

    fun ofColumn(column: Int) = (Rows.ROW_1..Rows.ROW_8).map { Cell.encode(it, column) }.toList()
}
