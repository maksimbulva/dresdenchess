package ru.maksimbulva.dresdenchess.moves

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.pieces.King

class MoveTableGeneratorTest {

    @Test
    fun `move array instances are shared between cell when possible`() {
        // TODO
//        assertTrue(KING_TABLE[Cells.B1] === KING_TABLE[Cells.C1])
//        assertTrue(KING_TABLE[Cells.B2] === KING_TABLE[Cells.C2])
//        assertTrue(KING_TABLE[Cells.B8] === KING_TABLE[Cells.G8])
    }

    companion object {
        // private val KING_TABLE = MoveTableGenerator.generateTable(King.moveDirs)
    }
}
