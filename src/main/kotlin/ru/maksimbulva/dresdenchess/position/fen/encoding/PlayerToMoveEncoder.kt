package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.position.Fen
import ru.maksimbulva.dresdenchess.position.Position

object PlayerToMoveEncoder : IEncoder {
    override fun encode(position: Position): String {
        return if (position.playerToMove == Players.WHITE) {
            Fen.WhiteToMove
        } else {
            Fen.BlackToMove
        }
    }
}
