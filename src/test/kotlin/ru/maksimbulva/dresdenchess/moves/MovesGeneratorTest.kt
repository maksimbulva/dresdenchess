package ru.maksimbulva.dresdenchess.moves

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.position.createInitialPosition

internal class MovesGeneratorTest {
    @Test
    // Also known as perft in computer chess literature
    fun `move count from initial position is correct`() {
        for ((depthPly, moveCount) in arrayOf(
            1 to 20L,
            2 to 400L,
            3 to 8902L,
            4 to 197_281L
        )) {
            val position = createInitialPosition()
            assertEquals(moveCount, MovesGenerator.countPossibleMoves(position, depthPly))
        }
    }
}
