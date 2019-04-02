package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.*
import ru.maksimbulva.dresdenchess.exceptions.InvalidPositionEncodingException
import ru.maksimbulva.dresdenchess.pieces.BlackPawn
import ru.maksimbulva.dresdenchess.pieces.IPiece
import ru.maksimbulva.dresdenchess.pieces.WhitePawn

/**
 * Provides methods for encoding and decoding chess positions stored in Forsyth–Edwards notation
 * https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 */
object Fen {
    enum class MoveCounterEncoding {
        UseActualValue,
        /**
         * Will write zero as a halfmove timer value.
         * Use this option to produce the same output as some other engines
         */
        SetCounterToZero
    }

    private const val ROWS_SEPARATOR = '/'
    private const val FEN_NO_ONE_CAN_CASTLE = '-'
    private const val FEN_WHITE_CAN_CASTLE_SHORT = 'K'
    private const val FEN_WHITE_CAN_CASTLE_LONG = 'Q'
    private const val FEN_BLACK_CAN_CASTLE_SHORT = 'k'
    private const val FEN_BLACK_CAN_CASTLE_LONG = 'q'
    private const val FEN_CANNOT_CAPTURE_EN_PASSANT = '-'

    const val InitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

    fun encode(position: Position, moveCounterEncoding: MoveCounterEncoding): String {
        val sb = StringBuilder(80)
        encodeBoard(sb, position.board)
        encodePlayerToMove(sb, position.playerToMove)
        encodeCastlingAvailability(sb, position)
        encodeEnPassantCaptureAvailability(sb, position)
        encodeMoveCounter(sb, position, moveCounterEncoding)
        return sb.toString()
    }

    fun decode(encoded: String): Position {
        try {
            val splited = encoded.split(ROWS_SEPARATOR, ' ', '\t')
            val pieces = decodePieces(splited)

            val whitePieces = pieces.asSequence()
                .filter { it.player == Players.WHITE }
                .map { it.cell to it.piece }

            val blackPieces = pieces.asSequence()
                .filter { it.player == Players.BLACK }
                .map { it.cell to it.piece }

            val playerToMove = decodePlayerToMove(splited[8])
            val castlingFlags = splited[9]
            val enPassantCaptureColumn = decodeEnPassantCaptureColumn(splited[10])
            // TODO: These guys are not so critical, forgive possible mistakes
            val halfmoveClock = splited[11].toInt()
            val fullmoveCounter = splited[12].toInt()
            // TODO
            return PositionHelper.create(
                white = whitePieces.toList(),
                black = blackPieces.toList(),
                playerToMove = playerToMove,
                isWhiteCanCastleShort = FEN_WHITE_CAN_CASTLE_SHORT in castlingFlags,
                isWhiteCanCastleLong = FEN_WHITE_CAN_CASTLE_LONG in castlingFlags,
                isBlackCanCastleShort = FEN_BLACK_CAN_CASTLE_SHORT in castlingFlags,
                isBlackCanCastleLong = FEN_BLACK_CAN_CASTLE_LONG in castlingFlags,
                enPassantCaptureColumn = enPassantCaptureColumn,
                halfmoveClock = halfmoveClock,
                fullmoveCounter = fullmoveCounter
            )
        } catch (e: IllegalArgumentException) {
            throw InvalidPositionEncodingException()
        }
    }
/*
        // TODO - add proper exception handling
        public static CreatePositionData Decode(string fen)
        {
            int cur_s_index = 0;
            var pieces = new List<PiecePosition>(32);
            for (int row = Chessboard.ROW_MAX; row >= Chessboard.ROW_MIN;
                --row, ++cur_s_index)
            {
                int column = Chessboard.COLUMN_MIN;
                foreach (var c in s[cur_s_index])
                {
                    if (column < Chessboard.COLUMN_MIN || column > Chessboard.COLUMN_MAX)
                    {
                        // TODO
                        throw new Exception();
                    }
                    if (char.IsDigit(c))
                    {
                        column += c - '0';
                    }
                    else
                    {
                        Players player;
                        Pieces piece;
                        if (CharToPiece(c, out player, out piece))
                        {
                            pieces.Add(new PiecePosition(player, piece,
                                new ChessboardCell(row, column)));
                            ++column;
                        }
                        else
                        {
                            // TODO
                            throw new Exception();
                        }
                    }
                }
            }

            ++cur_s_index;

            var s_castling = s[cur_s_index];
            var white_castling_options = Castle.None;
            var black_castling_options = Castle.None;
            if (s_castling.Contains(FEN_WHITE_CAN_CASTLE_SHORT))
            {
                white_castling_options |= Castle.Short;
            }
            else if (s_castling.Contains(FEN_WHITE_CAN_CASTLE_LONG))
            {
                white_castling_options |= Castle.Long;
            }
            else if (s_castling.Contains(FEN_BLACK_CAN_CASTLE_SHORT))
            {
                black_castling_options |= Castle.Short;
            }
            else if (s_castling.Contains(FEN_BLACK_CAN_CASTLE_LONG))
            {
                black_castling_options |= Castle.Long;
            }
            ++cur_s_index;


            // These guys are not so critical, forgive possible mistakes
            ushort halfmove_clock;
            if (!ushort.TryParse(s[cur_s_index + 1], out halfmove_clock))
            {
                halfmove_clock = 0;
            }

            ushort fullmove_number;
            if (!ushort.TryParse(s[cur_s_index + 2], out fullmove_number))
            {
                fullmove_number = 1;
            }
            fullmove_number = (ushort)Math.Max(1, (int)fullmove_number);

            return new CreatePositionData(pieces, player_to_move, white_castling_options,
                black_castling_options, capture_en_passant_column, fullmove_number,
                halfmove_clock);
        }

    }
}
     */

    private fun encodeBoard(sb: StringBuilder, board: Board) {
        for (row in Rows.ROW_8 downTo Rows.ROW_1) {
            var emptyCellsCounter = 0
            for (column in Columns.COLUMN_A..Columns.COLUMN_H) {
                val node = board.lookupCell(Cell.encode(row, column))
                if (node != null) {
                    if (emptyCellsCounter != 0) {
                        sb.append(emptyCellsCounter)
                        emptyCellsCounter = 0
                    }
                    sb.append(pieceToChar(node.player, node.piece))
                } else {
                    ++emptyCellsCounter
                }
            }
            if (emptyCellsCounter != 0) {
                sb.append(emptyCellsCounter)
            }
            if (row != Rows.ROW_1) {
                sb.append(ROWS_SEPARATOR)
            }
        }
    }

    private fun decodePieces(splited: List<String>): List<BoardCell> {
        require(splited.size >= 8)
        val result = mutableListOf<BoardCell>()
        splited.asSequence().take(8).forEachIndexed { index, s ->
            var column = Columns.COLUMN_A
            s.forEach {
                require(column in Columns.COLUMN_A..Columns.COLUMN_H)
                if (it.isDigit()) {
                    column += it - '0'
                } else {
                    val (player, piece) = charToPlayerAndPiece(it)
                    val row = Rows.ROW_8 - index
                    val cell = Cell.encode(row, column)
                    result.add(BoardCell(player, Pieces.instance(piece, player), cell))
                    ++column
                }
            }
        }
        return result
    }

    private fun encodePlayerToMove(sb: StringBuilder, player: Players) {
        sb.append(' ')
        sb.append(if (player == Players.WHITE) 'w' else 'b')
    }

    private fun decodePlayerToMove(encoded: String): Players {
        // Excuse case mismatch
        return when (encoded.firstOrNull()) {
            'w' -> Players.WHITE
            'b' -> Players.BLACK
            else -> throw InvalidPositionEncodingException()
        }
    }

    private fun encodeCastlingAvailability(sb: StringBuilder, position: Position) {
        sb.append(' ')
        val availableCastlings = listOf(
            position.isWhiteCanCastleShort to FEN_WHITE_CAN_CASTLE_SHORT,
            position.isWhiteCanCastleLong to FEN_WHITE_CAN_CASTLE_LONG,
            position.isBlackCanCastleShort to FEN_BLACK_CAN_CASTLE_SHORT,
            position.isBlackCanCastleLong to FEN_BLACK_CAN_CASTLE_LONG
        )
            .filter { it.first }

        if (availableCastlings.isEmpty()) {
            sb.append(FEN_NO_ONE_CAN_CASTLE)
        } else {
            availableCastlings.forEach { sb.append(it.second) }
        }
    }

    private fun encodeEnPassantCaptureAvailability(sb: StringBuilder, position: Position) {
        val flags = position.flags
        sb.append(' ')
        if (PositionFlags.isCanCaptureEnPassant(flags)) {
            val row = if (position.playerToMove == Players.WHITE) {
                WhitePawn.enPassantCaptureRow
            } else {
                BlackPawn.enPassantCaptureRow
            }
            sb.append(Cell.toString(Cell.encode(row, PositionFlags.enPassantColumn(flags))))
        } else {
            sb.append(FEN_CANNOT_CAPTURE_EN_PASSANT)
        }
    }

    private fun decodeEnPassantCaptureColumn(encoded: String): Int? {
        val cell = Cell.fromStringOrNull(encoded) ?: return null
        return Cell.row(cell)
    }

    private fun encodeMoveCounter(
        sb: StringBuilder,
        position: Position,
        moveCounterEncoding: MoveCounterEncoding
    ) {
        val fullmoveCounter = if (moveCounterEncoding == MoveCounterEncoding.UseActualValue) {
            position.fullmoveCounter
        } else {
            1
        }
        sb.append(' ').append(position.halfmoveClock).append(' ').append(fullmoveCounter)
    }

    private fun pieceToChar(player: Players, piece: IPiece): Char {
        val char = when (piece.piece) {
            Pieces.PAWN -> 'p'
            Pieces.KNIGHT -> 'n'
            Pieces.BISHOP -> 'b'
            Pieces.ROOK -> 'r'
            Pieces.QUEEN -> 'q'
            Pieces.KING -> 'k'
            else -> throw IllegalStateException()
        }
        return if (player == Players.WHITE) {
            char.toUpperCase()
        } else {
            char
        }
    }

    private fun charToPlayerAndPiece(c: Char): Pair<Players, Int>
    {
        val player = if (c.isUpperCase()) {
            Players.WHITE
        } else {
            Players.BLACK
        }
        return player to when (c.toLowerCase()) {
            'p' -> Pieces.PAWN
            'n' -> Pieces.KNIGHT
            'b' -> Pieces.BISHOP
            'r' -> Pieces.ROOK
            'q' -> Pieces.QUEEN
            'k' -> Pieces.KING
            else -> throw InvalidPositionEncodingException()
        }
    }
}
