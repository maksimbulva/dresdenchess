package ru.maksimbulva.dresdenchess.position

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*

internal class FenDecodeTest {

    @Test
    fun decodeInitialPosition() {
        val position = Fen.decode("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
        assertEquals(initialPositionBoard(), position.board)
    }
}
