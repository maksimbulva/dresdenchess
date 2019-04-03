package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.position.Position

object HalfmoveClockEncoder : IEncoder {
    override fun encode(position: Position) = position.halfmoveClock.toString()
}
