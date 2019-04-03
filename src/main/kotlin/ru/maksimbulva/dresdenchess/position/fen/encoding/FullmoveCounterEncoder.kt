package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.position.Fen
import ru.maksimbulva.dresdenchess.position.Position

class FullmoveCounterEncoder(private val moveCounterEncoding: Fen.MoveCounterEncoding) : IEncoder {
    override fun encode(position: Position): String {
        return if (moveCounterEncoding == Fen.MoveCounterEncoding.UseActualValue) {
            position.fullmoveCounter
        } else {
            1
        }.toString()
    }
}
