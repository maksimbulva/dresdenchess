package ru.maksimbulva.dresdenchess.position


object PositionFlags {
    private const val FLAG_CAN_CAPTURE_EN_PASSANT = 1
    private const val EN_PASSANT_CAPTURE_MASK = 15.inv()

    fun isCanCaptureEnPassant(flags: Int) =
        (flags and FLAG_CAN_CAPTURE_EN_PASSANT) != 0

    fun enPassantColumn(flags: Int) = (flags shr 1) and 7

    fun setEnPassantCaptureColumn(flags: Int, column: Int?): Int {
        val result = flags and EN_PASSANT_CAPTURE_MASK
        return if (column == null) {
            result
        } else {
            result or FLAG_CAN_CAPTURE_EN_PASSANT or (column shl 1)
        }
    }
}
