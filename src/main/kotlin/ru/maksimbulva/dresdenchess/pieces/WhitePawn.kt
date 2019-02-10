package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.board.Rows

object WhitePawn : Pawn(
    moveRowDelta = 1,
    doubleStepMoveRow = Rows.ROW_2,
    enPassantCaptureRow = Rows.ROW_5
)
