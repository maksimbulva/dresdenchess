package ru.maksimbulva.dresdenchess.board

object Cells {
    const val A1 = 0
    const val E1 = (Rows.ROW_1 shl 3) + Columns.COLUMN_E
    const val E8 = (Rows.ROW_8 shl 3) + Columns.COLUMN_E
    const val H1 = 7
    const val A8 = 56
    const val H8 = 63

    fun ofRow(row: Int) = IntRange(Cell.encode(row, Columns.COLUMN_A), Cell.encode(row, Columns.COLUMN_H))

    fun ofColumn(column: Int) = (Rows.ROW_1..Rows.ROW_8).map { Cell.encode(it, column) }.toList()
}
