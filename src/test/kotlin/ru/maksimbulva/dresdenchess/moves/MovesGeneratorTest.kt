package ru.maksimbulva.dresdenchess.moves

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.position.createInitialPosition

internal class MovesGeneratorTest {
    @Test
    fun `white move count from initial position is correct`() {
        val position = createInitialPosition()
        assertEquals(20, MovesGenerator.countPossibleMoves(position, depthPly = 1))
    }
}