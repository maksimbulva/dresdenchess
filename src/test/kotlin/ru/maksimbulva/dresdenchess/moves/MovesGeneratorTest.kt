package ru.maksimbulva.dresdenchess.moves

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.position.createInitialPosition

internal class MovesGeneratorTest {
    @Test
    // Also known as perft in computer chess literature
    fun `move count from initial position is correct`() {
        for ((depthPly, moveCount) in listOf(
            1 to 20L,
            2 to 400L
        )) {
            val position = createInitialPosition()
            assertEquals(moveCount, MovesGenerator.countPossibleMoves(position, depthPly))
        }
    }
}
