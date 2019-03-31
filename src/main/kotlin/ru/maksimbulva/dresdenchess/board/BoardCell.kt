package ru.maksimbulva.dresdenchess.board

import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.pieces.IPiece

data class BoardCell(val player: Players, val piece: IPiece, val cell: Int)
