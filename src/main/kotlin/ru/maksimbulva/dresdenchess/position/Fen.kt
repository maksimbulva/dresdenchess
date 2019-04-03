package ru.maksimbulva.dresdenchess.position

import ru.maksimbulva.dresdenchess.Pieces
import ru.maksimbulva.dresdenchess.Players
import ru.maksimbulva.dresdenchess.board.BoardCell
import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.exceptions.InvalidPositionEncodingException
import ru.maksimbulva.dresdenchess.pieces.IPiece
import ru.maksimbulva.dresdenchess.position.fen.decoding.BoardDecoder
import ru.maksimbulva.dresdenchess.position.fen.decoding.EnPassantCaptureAvailabilityDecoder
import ru.maksimbulva.dresdenchess.position.fen.decoding.PlayerToMoveDecoder
import ru.maksimbulva.dresdenchess.position.fen.encoding.*

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

    const val WhiteToMove = "w"
    const val BlackToMove = "b"

    const val ROWS_SEPARATOR = '/'
    const val FEN_NO_ONE_CAN_CASTLE = '-'
    const val FEN_WHITE_CAN_CASTLE_SHORT = 'K'
    const val FEN_WHITE_CAN_CASTLE_LONG = 'Q'
    const val FEN_BLACK_CAN_CASTLE_SHORT = 'k'
    const val FEN_BLACK_CAN_CASTLE_LONG = 'q'
    const val CannotCaptureEnPassant = "-"

    const val InitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

    fun encode(position: Position, moveCounterEncoding: MoveCounterEncoding): String {
        return arrayOf(
            BoardEncoder,
            PlayerToMoveEncoder,
            CastlingAvailabilityEncoder,
            EnPassantCaptureAvailabilityEncoder,
            HalfmoveClockEncoder,
            FullmoveCounterEncoder(moveCounterEncoding)
        )
            .map { it.encode(position) }
            .joinToString(separator = " ")
    }

    fun decode(encoded: String): Position {
        try {
            val splited = encoded.split(' ', '\t')
            val board = BoardDecoder.decode(splited.first())
            val playerToMove = PlayerToMoveDecoder.decode(splited[1])
            val castlingFlags = splited[2]
            val enPassantCaptureColumn = EnPassantCaptureAvailabilityDecoder.decode(splited[3])
            // TODO: These guys are not so critical, forgive possible mistakes
            val halfmoveClock = splited[4].toInt()
            val fullmoveCounter = splited[5].toInt()
            // TODO
            return PositionHelper.create(
                board = board,
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

    private fun decodeEnPassantCaptureColumn(encoded: String): Int? {
        val cell = Cell.fromStringOrNull(encoded) ?: return null
        return Cell.row(cell)
    }
}
