package ru.maksimbulva.dresdenchess

// data class Move(val from: Int, val to: Int)

object Move {
    private const val CAPTURE_BIT = 1 shl 15
    private const val PIECE_MASK = Pieces.MASK
    private const val CELL_MASK = 63

    fun encode(piece: Int, from: Int, to: Int): Int {
        return (piece shl 12) + (from shl 6) + to
    }

    fun encodeCapture(piece: Int, from: Int, to: Int, capturedPiece: Int): Int {
        return encode(piece, from, to) + CAPTURE_BIT + (capturedPiece shl 16)
    }

    fun piece(move: Int) = (move shr 12) and PIECE_MASK
    fun fromCell(move: Int) = (move shr 6) and CELL_MASK
    fun destCell(move: Int) = move and CELL_MASK
    fun isCapture(move: Int) = (move and CAPTURE_BIT) != 0
    fun capturedPiece(move: Int) = (move shr 16) and PIECE_MASK
    // TODO
    fun isEnPassantCapture(move: Int) = false
}
