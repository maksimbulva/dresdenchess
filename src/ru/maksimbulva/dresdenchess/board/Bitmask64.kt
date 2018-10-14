package ru.maksimbulva.dresdenchess.board

class Bitmask64 {
    private var data: Long = 0L

    fun setEmpty(cell: Int) {
        val bit = 1L shl cell
        data = data and (bit.inv())
    }

    fun setOccupied(cell: Int) {
        data = data or (1L shl cell)
    }

    fun isEmpty(cell: Int) = (data and (1L shl cell)) == 0L

    fun isOccupied(cell: Int) = (data and (1L shl cell)) != 0L
}