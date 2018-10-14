package dataStructs

class BoardPiecesLinkedList(private val board: Array<Int>, rootData: Int, rootCell: Int) {
    companion object {
        private const val CELL_NONE = 127

        private fun encode(data: Int, cell: Int, prevCell: Int, nextCell: Int) =
            data + (cell shl 8) + (prevCell shl 16) + (nextCell shl 24)

        private fun getCell(encoded: Int, cell: Int) = (encoded and 0x7FFF00FF) or (cell shl 8)
        private fun setPrevCell(encoded: Int, cell: Int) = (encoded and 0x7F00FFFF) or (cell shl 16)
        private fun setNextCell(encoded: Int, cell: Int) = (encoded and 0x00FFFFFF) or (cell shl 24)

        private fun getCell(encoded: Int) = (encoded shr 8) and 255
        private fun getPrevCell(encoded: Int) = (encoded shr 16) and 255
        private fun getNextCell(encoded: Int) = (encoded shr 24) and 255
    }

    private var encodedRoot = encode(rootData, rootCell, CELL_NONE, CELL_NONE)

    init {
        board[rootCell] = encodedRoot
    }

    fun add(data: Int, cell: Int) {
        val rootNextCell = getNextCell(encodedRoot)
        val newEncoded = encode(data, cell, getCell(encodedRoot), rootNextCell)
        encodedRoot = setNextCell(encodedRoot, cell)
        board[rootNextCell] = setPrevCell(board[rootNextCell], cell)
        board[cell] = newEncoded
    }
}