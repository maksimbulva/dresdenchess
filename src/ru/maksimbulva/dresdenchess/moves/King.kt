package ru.maksimbulva.dresdenchess.moves

import ru.maksimbulva.dresdenchess.Move
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.position.Position

object King {
    private val MOVES_ALL = listOf(
            Cell.MOVE_DOWN_LEFT,
            Cell.MOVE_DOWN,
            Cell.MOVE_DOWN_RIGHT,
            Cell.MOVE_LEFT,
            Cell.MOVE_RIGHT,
            Cell.MOVE_UP_LEFT,
            Cell.MOVE_UP,
            Cell.MOVE_UP_RIGHT
    )

    private val MOVES_ALL_EXCEPT_DOWN = listOf(
            Cell.MOVE_LEFT,
            Cell.MOVE_RIGHT,
            Cell.MOVE_UP_LEFT,
            Cell.MOVE_UP,
            Cell.MOVE_UP_RIGHT
    )

    private val MOVES_ALL_EXCEPT_UP = listOf(
            Cell.MOVE_DOWN_LEFT,
            Cell.MOVE_DOWN,
            Cell.MOVE_DOWN_RIGHT,
            Cell.MOVE_LEFT,
            Cell.MOVE_RIGHT
    )

    private val MOVES_ALL_EXCEPT_LEFT = listOf(
            Cell.MOVE_DOWN,
            Cell.MOVE_DOWN_RIGHT,
            Cell.MOVE_RIGHT,
            Cell.MOVE_UP,
            Cell.MOVE_UP_RIGHT
    )

    private val MOVES_ALL_EXCEPT_RIGHT = listOf(
            Cell.MOVE_DOWN_LEFT,
            Cell.MOVE_DOWN,
            Cell.MOVE_LEFT,
            Cell.MOVE_UP_LEFT,
            Cell.MOVE_UP
    )

    private val AVAILABLE_MOVES = Array(64) { MOVES_ALL }

    init {
        Cells.ofRow(Rows.ROW_1).forEach { AVAILABLE_MOVES[it] = MOVES_ALL_EXCEPT_DOWN }
        Cells.ofRow(Rows.ROW_8).forEach { AVAILABLE_MOVES[it] = MOVES_ALL_EXCEPT_UP }
        Cells.ofColumn(Columns.COLUMN_A).forEach { AVAILABLE_MOVES[it] = MOVES_ALL_EXCEPT_LEFT }
        Cells.ofColumn(Columns.COLUMN_H).forEach { AVAILABLE_MOVES[it] = MOVES_ALL_EXCEPT_RIGHT }
        AVAILABLE_MOVES[Cells.A1] = listOf(Cell.MOVE_RIGHT, Cell.MOVE_UP, Cell.MOVE_UP_RIGHT)
        AVAILABLE_MOVES[Cells.H1] = listOf(Cell.MOVE_LEFT, Cell.MOVE_UP_LEFT, Cell.MOVE_UP)
        AVAILABLE_MOVES[Cells.A8] = listOf(Cell.MOVE_DOWN, Cell.MOVE_DOWN_RIGHT, Cell.MOVE_RIGHT)
        AVAILABLE_MOVES[Cells.H8] = listOf(Cell.MOVE_DOWN_LEFT, Cell.MOVE_DOWN, Cell.MOVE_LEFT)
    }

    fun generateSemiLegalMoves(position: Position, from: Int, moves: MutableList<Int>) {
        val board = position.board
        for (moveDir in AVAILABLE_MOVES[from]) {
            val toCell = from + moveDir
            moves.add(Move.encode(from, from + moveDir))
        }
    }
}