package ru.maksimbulva.dresdenchess.position.fen.decoding

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.exceptions.InvalidPositionEncodingException
import ru.maksimbulva.dresdenchess.position.Fen

object PlayerToMoveDecoder {
    fun decode(encoded: String): Players {
        if (encoded.length == 1) {
            return when (encoded.substring(0..0).toLowerCase()) {
                Fen.WhiteToMove -> Players.WHITE
                Fen.BlackToMove -> Players.BLACK
                else -> throw InvalidPositionEncodingException()
            }
        }
        throw InvalidPositionEncodingException()
    }
}
