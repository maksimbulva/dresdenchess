package ru.maksimbulva.dresdenchess.pieces

import ru.maksimbulva.dresdenchess.board.Rows

object BlackPawn : Pawn(
    moveRowDelta = -1,
    doubleStepMoveRow = Rows.ROW_7,
    enPassantCaptureRow = Rows.ROW_4
)
