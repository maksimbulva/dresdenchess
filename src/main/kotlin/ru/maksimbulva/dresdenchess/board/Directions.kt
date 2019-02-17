package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Pieces

const val DIR_DOWN_LEFT = Cells.A1 - Cells.B2
const val DIR_DOWN = Cells.B1 - Cells.B2
const val DIR_DOWN_RIGHT = Cells.C1 - Cells.B2
const val DIR_LEFT = Cells.A2 - Cells.B2
const val DIR_RIGHT = Cells.C2 - Cells.B2
const val DIR_UP_LEFT = Cells.A3 - Cells.B2
const val DIR_UP = Cells.B3 - Cells.B2
const val DIR_UP_RIGHT = Cells.C3 - Cells.B2

const val DIR_DOWN_LEFT_ID = 0
const val DIR_DOWN_ID = 1
const val DIR_DOWN_RIGHT_ID = 2
const val DIR_LEFT_ID = 3
const val DIR_RIGHT_ID = 4
const val DIR_UP_LEFT_ID = 5
const val DIR_UP_ID = 6
const val DIR_UP_RIGHT_ID = 7

val DIRECTIONS = arrayOf(
    DirectionDownLeft,
    DirectionDown,
    DirectionDownRight,
    DirectionLeft,
    DirectionRight,
    DirectionUpLeft,
    DirectionUp,
    DirectionUpRight
)

sealed class Direction(
    val id: Int,
    val moveDelta: Int,
    val piece: Int,
    val maxLineLength: (Int) -> Int
)

object DirectionDownLeft : Direction(
    DIR_DOWN_LEFT_ID,
    DIR_DOWN_LEFT,
    Pieces.BISHOP,
    { cell -> Math.min(Cell.row(cell), Cell.column(cell)) }
)

object DirectionDown : Direction(
    DIR_DOWN_ID,
    DIR_DOWN,
    Pieces.ROOK,
    { cell -> Cell.row(cell) }
)

object DirectionDownRight : Direction(
    DIR_DOWN_RIGHT_ID,
    DIR_DOWN_RIGHT,
    Pieces.BISHOP,
    { cell -> Math.min(Cell.row(cell), 7 - Cell.column(cell)) }
)

object DirectionLeft : Direction(
    DIR_LEFT_ID,
    DIR_LEFT,
    Pieces.ROOK,
    { cell -> Cell.column(cell) }
)

object DirectionRight : Direction(
    DIR_RIGHT_ID,
    DIR_RIGHT,
    Pieces.ROOK,
    { cell -> 7 - Cell.column(cell) }
)

object DirectionUpLeft : Direction(
    DIR_UP_LEFT_ID,
    DIR_UP_LEFT,
    Pieces.BISHOP,
    { cell -> Math.min(7 - Cell.row(cell), Cell.column(cell)) }
)

object DirectionUp : Direction(
    DIR_UP_ID,
    DIR_UP,
    Pieces.ROOK,
    { cell -> 7 - Cell.row(cell) }
)

object DirectionUpRight : Direction(
    DIR_UP_RIGHT_ID,
    DIR_UP_RIGHT,
    Pieces.BISHOP,
    { cell -> Math.min(7 - Cell.row(cell), 7 - Cell.column(cell)) }
)
