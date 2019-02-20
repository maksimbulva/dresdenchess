package ru.maksimbulva.dresdenchess.position

object PositionFlags {
    private const val FLAG_CAN_CAPTURE_EN_PASSANT = 1
    private const val EN_PASSANT_CAPTURE_MASK = 15.inv()

    private const val FLAG_WHITE_CAN_CASTLE_SHORT = 1 shl 4
    private const val FLAG_WHITE_CAN_CASTLE_LONG = 1 shl 5
    private const val FLAG_BLACK_CAN_CASTLE_SHORT = 1 shl 6
    private const val FLAG_BLACK_CAN_CASTLE_LONG = 1 shl 7

    private const val FLAG_CAN_CASTLE_EXISTS = 15 shl 4

    fun isCanCaptureEnPassant(flags: Int) = checkFlag(flags, FLAG_CAN_CAPTURE_EN_PASSANT)

    fun enPassantColumn(flags: Int) = (flags shr 1) and 7

    fun setEnPassantCaptureColumn(flags: Int, column: Int?): Int {
        val result = flags and EN_PASSANT_CAPTURE_MASK
        return if (column == null) {
            result
        } else {
            result or FLAG_CAN_CAPTURE_EN_PASSANT or (column shl 1)
        }
    }

    fun isWhiteCanCastleShort(flags: Int) = checkFlag(flags, FLAG_WHITE_CAN_CASTLE_SHORT)
    fun setWhiteCanCastleShort(flags: Int, value: Boolean) =
        setFlag(flags, FLAG_WHITE_CAN_CASTLE_SHORT, value)

    fun isWhiteCanCastleLong(flags: Int) = checkFlag(flags, FLAG_WHITE_CAN_CASTLE_LONG)
    fun setWhiteCanCastleLong(flags: Int, value: Boolean) =
        setFlag(flags, FLAG_WHITE_CAN_CASTLE_LONG, value)

    fun isBlackCanCastleShort(flags: Int) = checkFlag(flags, FLAG_BLACK_CAN_CASTLE_SHORT)
    fun setBlackCanCastleShort(flags: Int, value: Boolean) =
        setFlag(flags, FLAG_BLACK_CAN_CASTLE_SHORT, value)

    fun isBlackCanCastleLong(flags: Int) = checkFlag(flags, FLAG_BLACK_CAN_CASTLE_LONG)
    fun setBlackCanCastleLong(flags: Int, value: Boolean) =
        setFlag(flags, FLAG_BLACK_CAN_CASTLE_LONG, value)

    fun isCanCastleExists(flags: Int) = checkFlag(flags, FLAG_CAN_CASTLE_EXISTS)

    private fun checkFlag(positionFlags: Int, flag: Int): Boolean {
        return (positionFlags and flag) != 0
    }

    private fun setFlag(positionFlags: Int, flag: Int, value: Boolean): Int {
        return if (value) {
            positionFlags or flag
        } else {
            positionFlags and flag.inv()
        }
    }
}
