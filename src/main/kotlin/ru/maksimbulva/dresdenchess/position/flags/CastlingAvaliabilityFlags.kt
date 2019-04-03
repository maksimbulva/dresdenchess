package ru.maksimbulva.dresdenchess.position.flags

data class CastlingAvaliabilityFlags(val canCastleShort: Boolean, val canCastleLong: Boolean) {

    companion object {
        val cannotCastle = CastlingAvaliabilityFlags(
            canCastleShort = false,
            canCastleLong = false
        )

        val shortOnly = CastlingAvaliabilityFlags(
            canCastleShort = true,
            canCastleLong = false
        )

        val longOnly = CastlingAvaliabilityFlags(
            canCastleShort = false,
            canCastleLong = true
        )

        val both = CastlingAvaliabilityFlags(
            canCastleShort = true,
            canCastleLong = true
        )

        fun of(canCastleShort: Boolean, canCastleLong: Boolean): CastlingAvaliabilityFlags {
            return if (canCastleShort) {
                if (canCastleLong) {
                    both
                } else {
                    shortOnly
                }
            } else {
                if (canCastleLong) {
                    longOnly
                } else {
                    cannotCastle
                }
            }
        }
    }
}