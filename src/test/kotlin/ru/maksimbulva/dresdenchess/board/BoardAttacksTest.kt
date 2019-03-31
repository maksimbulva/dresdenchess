package ru.maksimbulva.dresdenchess.board

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.pieces.*

internal class BoardAttacksTest {

    @Test
    fun kingAndPawnsAttacksTest() {
        val board = Board(Cells.B2, Cells.D2).apply {
            addPiece(BoardCell(Players.WHITE, WhitePawn, Cells.C3))
            addPiece(BoardCell(Players.BLACK, BlackPawn, Cells.B3))
        }

        checkCellsAttacked(
            cells = setOf(
                Cells.A1,
                Cells.B1,
                Cells.C1,
                Cells.A2,
                Cells.B2,
                Cells.C2,
                Cells.A3,
                Cells.B3,
                Cells.C3,
                Cells.B4,
                Cells.D4
            ),
            attacker = Players.WHITE,
            board = board
        )

        checkCellsAttacked(
            cells = setOf(
                Cells.C1,
                Cells.D1,
                Cells.E1,
                Cells.C2,
                Cells.D2,
                Cells.E2,
                Cells.C3,
                Cells.D3,
                Cells.E3,
                Cells.A2,
                Cells.C2
            ),
            attacker = Players.BLACK,
            board = board
        )
    }

    @Test
    fun knightAttackTest() {
        val board = Board(Cells.A1, Cells.H8).apply {
            addPiece(BoardCell(Players.WHITE, Knight, Cells.F3))
            addPiece(BoardCell(Players.BLACK, Knight, Cells.A8))
        }

        checkCellsAttacked(
            cells = setOf(
                Cells.A1,
                Cells.B1,
                Cells.A2,
                Cells.B2,
                Cells.E1,
                Cells.G1,
                Cells.D2,
                Cells.H2,
                Cells.D4,
                Cells.H4,
                Cells.E5,
                Cells.G5
            ),
            attacker = Players.WHITE,
            board = board
        )

        checkCellsAttacked(
            cells = setOf(
                Cells.G7,
                Cells.H7,
                Cells.G8,
                Cells.H8,
                Cells.B6,
                Cells.C7
            ),
            attacker = Players.BLACK,
            board = board
        )
    }

    @Test
    fun bishopAttackTest() {
        val board = Board(Cells.A1, Cells.H8).apply {
            addPiece(BoardCell(Players.WHITE, Bishop, Cells.F3))
        }

        checkCellsAttacked(
            cells = setOf(
                Cells.A1,
                Cells.B1,
                Cells.A2,
                Cells.B2,
                Cells.D1,
                Cells.E2,
                Cells.G4,
                Cells.H5,
                Cells.H1,
                Cells.G2,
                Cells.E4,
                Cells.D5,
                Cells.C6,
                Cells.B7,
                Cells.A8
            ),
            attacker = Players.WHITE,
            board = board
        )

        checkCellsAttacked(
            cells = setOf(
                Cells.G7,
                Cells.H7,
                Cells.G8,
                Cells.H8
            ),
            attacker = Players.BLACK,
            board = board
        )
    }

    @Test
    fun rookAttackTest() {
        val board = Board(Cells.A1, Cells.H8).apply {
            addPiece(BoardCell(Players.BLACK, Rook, Cells.F3))
        }

        checkCellsAttacked(
            cells = setOf(
                Cells.A1,
                Cells.B1,
                Cells.A2,
                Cells.B2
            ),
            attacker = Players.WHITE,
            board = board
        )

        checkCellsAttacked(
            cells = setOf(
                Cells.G7,
                Cells.H7,
                Cells.G8,
                Cells.H8,
                Cells.A3,
                Cells.B3,
                Cells.C3,
                Cells.D3,
                Cells.E3,
                Cells.G3,
                Cells.H3,
                Cells.F1,
                Cells.F2,
                Cells.F4,
                Cells.F5,
                Cells.F6,
                Cells.F7,
                Cells.F8
            ),
            attacker = Players.BLACK,
            board = board
        )
    }

    companion object {

        private fun checkCellsAttacked(
            cells: Set<Int>,
            attacker: Players,
            board: Board
        ) {
            for (cell in Cells.A1..Cells.H8) {
                if (cell in cells) {
                    assertTrue(board.isCellAttacked(cell, attacker), Cell.toString(cell))
                } else {
                    assertFalse(board.isCellAttacked(cell, attacker), Cell.toString(cell))
                }
            }
        }
    }
}