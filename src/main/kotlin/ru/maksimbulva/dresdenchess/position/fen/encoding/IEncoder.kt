package ru.maksimbulva.dresdenchess.position.fen.encoding

import ru.maksimbulva.dresdenchess.position.Position

interface IEncoder {
    fun encode(position: Position): String
}
