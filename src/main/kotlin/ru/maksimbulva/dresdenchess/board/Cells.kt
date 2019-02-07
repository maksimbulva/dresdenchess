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
    const val A2 = 8
    const val B2 = 9
    const val C2 = 10
    const val A3 = 16
    const val B3 = 17
    const val C3 = 18
    const val E8 = (Rows.ROW_8 shl 3) + Columns.COLUMN_E
    const val A8 = 56
    const val B8 = 57
    const val G8 = 62
    const val H8 = 63


    fun ofRow(row: Int) = IntRange(Cell.encode(row, Columns.COLUMN_A), Cell.encode(row, Columns.COLUMN_H))

    fun ofColumn(column: Int) = (Rows.ROW_1..Rows.ROW_8).map { Cell.encode(it, column) }.toList()
}
