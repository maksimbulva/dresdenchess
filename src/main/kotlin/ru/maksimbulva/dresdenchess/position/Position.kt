package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.Board
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.moves.Move
import java.util.*

class Position(whiteKingCell: Int, blackKingCell: Int) {
    val board = Board(whiteKingCell, blackKingCell)

    private val movesPlayed = Stack<MovePlayed>()

    private var _flags: Int = 0
    val flags: Int get() = _flags

    var isWhiteCanCastleShort: Boolean
        get() = PositionFlags.isWhiteCanCastleShort(flags)
        set(value) {
            _flags = PositionFlags.setWhiteCanCastleShort(flags, value)
        }

    var isWhiteCanCastleLong: Boolean
        get() = PositionFlags.isWhiteCanCastleLong(flags)
        set(value) {
            _flags = PositionFlags.setWhiteCanCastleLong(flags, value)
        }

    var isBlackCanCastleShort: Boolean
        get() = PositionFlags.isBlackCanCastleShort(flags)
        set(value) {
            _flags = PositionFlags.setBlackCanCastleShort(flags, value)
        }

    var isBlackCanCastleLong: Boolean
        get() = PositionFlags.isBlackCanCastleLong(flags)
        set(value) {
            _flags = PositionFlags.setBlackCanCastleLong(flags, value)
        }

    var _playerToMove = Players.WHITE
    val playerToMove get() = _playerToMove
    val otherPlayer get() = Players.BLACK - _playerToMove

    private val _halfmoveClock: Int = 0
    val halfmoveClock: Int get() = _halfmoveClock

    private val _fullmoveCounter: Int = 1
    val fullmoveCounter: Int get() = _fullmoveCounter

    val isCanCaptureEnPassant
        get() = PositionFlags.isCanCaptureEnPassant(_flags)

    val enPassantCaptureColumn
        get() = PositionFlags.enPassantColumn(_flags)

    fun playMove(move: Int) {
        movesPlayed.add(MovePlayed(move, _flags))
        val piece = Move.piece(move)
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        if (Move.isCapture(move)) {
            if (Move.isEnPassantCapture(move)) {
                val capturedPieceCell = enPassantCaptureCapturedPieceCell(fromCell, toCell)
                // TODO - remove me
                assert(board.lookupCell(capturedPieceCell)!!.piece.piece == Pieces.PAWN)
                board.removePieceAt(capturedPieceCell)
            } else {
                board.removePieceAt(toCell)
            }
        }
        board.updatePieceCell(fromCell, toCell)

        val enPassantColumn: Int? = if (
            piece == Pieces.PAWN && Math.abs(fromCell - toCell) == 16
        ) {
            Cell.column(fromCell)
        } else {
            null
        }

        _flags = PositionFlags.setEnPassantCaptureColumn(_flags, enPassantColumn)

        flipPlayerToMove()
    }

    fun undoMove() {
        val movePlayed = movesPlayed.pop()
        undoMove(movePlayed.move)
        _flags = movePlayed.savedFlags
    }

    private fun undoMove(move: Int) {
        val fromCell = Move.fromCell(move)
        val toCell = Move.destCell(move)
        board.updatePieceCell(toCell, fromCell)
        if (Move.isCapture(move)) {
            if (Move.isEnPassantCapture(move)) {
                val capturedPieceCell = enPassantCaptureCapturedPieceCell(fromCell, toCell)
                val capturedPiece = Pieces.instance(Pieces.PAWN, playerToMove)
                board.addPiece(playerToMove, capturedPiece, capturedPieceCell)
            } else {
                val capturedPiece = Pieces.instance(Move.capturedPiece(move), playerToMove)
                board.addPiece(playerToMove, capturedPiece, toCell)
            }
        }
        flipPlayerToMove()
    }

    private fun flipPlayerToMove() {
        _playerToMove = otherPlayer
    }

    private class MovePlayed(val move: Int, val savedFlags: Int)
}

private fun enPassantCaptureCapturedPieceCell(fromCell: Int, toCell: Int): Int {
    return Cell.encode(Cell.row(fromCell), Cell.column(toCell))
}
