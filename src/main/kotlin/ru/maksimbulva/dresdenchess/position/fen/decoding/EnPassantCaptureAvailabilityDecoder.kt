package ru.maksimbulva.dresdenchess.position.fen.decoding

import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.exceptions.InvalidPositionEncodingException
import ru.maksimbulva.dresdenchess.position.Fen

object EnPassantCaptureAvailabilityDecoder {
    fun decode(encoded: String): Int? {
        return if (encoded == Fen.CannotCaptureEnPassant) {
            null
        } else {
            Cell.fromStringOrNull(encoded) ?: throw InvalidPositionEncodingException()
        }
    }
}