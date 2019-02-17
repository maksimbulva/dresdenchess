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
    const val D2 = 11
    const val E2 = 12
    const val F2 = 13
    const val G2 = 14
    const val H2 = 15
    const val A3 = 16
    const val B3 = 17
    const val C3 = 18
    const val D3 = 19
    const val E3 = 20
    const val F3 = 21
    const val G3 = 22
    const val H3 = 23
    const val A4 = 24
    const val B4 = 25
    const val C4 = 26
    const val D4 = 27
    const val E4 = 28
    const val F4 = 29
    const val G4 = 30
    const val H4 = 31
    const val A5 = 32
    const val B5 = 33
    const val C5 = 34
    const val D5 = 35
    const val E5 = 36
    const val F5 = 37
    const val G5 = 38
    const val H5 = 39
    const val A6 = 40
    const val B6 = 41
    const val C6 = 42
    const val D6 = 43
    const val E6 = 44
    const val F6 = 45
    const val G6 = 46
    const val H6 = 47
    const val A7 = 48
    const val B7 = 49
    const val C7 = 50
    const val D7 = 51
    const val E7 = 52
    const val F7 = 53
    const val G7 = 54
    const val H7 = 55
    const val A8 = 56
    const val B8 = 57
    const val C8 = 58
    const val D8 = 59
    const val E8 = 60
    const val F8 = 61
    const val G8 = 62
    const val H8 = 63


    fun ofRow(row: Int) = IntRange(Cell.encode(row, Columns.COLUMN_A), Cell.encode(row, Columns.COLUMN_H))

    fun ofColumn(column: Int) = (Rows.ROW_1..Rows.ROW_8).map { Cell.encode(it, column) }.toList()
}
