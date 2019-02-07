package ru.maksimbulva.dresdenchess.position

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
    private const val FEN_WHITE_TO_MOVE = 'w'
    private const val FEN_BLACK_TO_MOVE = 'b'
    private const val FEN_NO_ONE_CAN_CASTLE = '-'
    private const val FEN_WHITE_CAN_CASTLE_SHORT = 'K'
    private const val FEN_WHITE_CAN_CASTLE_LONG = 'Q'
    private const val FEN_BLACK_CAN_CASTLE_SHORT = 'k'
    private const val FEN_BLACK_CAN_CASTLE_LONG = 'q'
    private const val FEN_CANNOT_CAPTURE_EN_PASSANT = '-'

    const val InitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

/*    public static class Fen
    {

        public static readonly string InitialPosition =
            ;

        public static string Encode(IReadOnlyPosition position,
            EncodingOptions options = EncodingOptions.None)
        {
            Debug.Assert(position != null);
            var s = new StringBuilder(80);
            // Encode the chessboard
            var board = Factory.Instance.CreateChessboardArray(position);
            for (int row = Chessboard.ROW_MAX; row >= Chessboard.ROW_MIN; --row)
            {
                int empty_cells = 0;
                for (int column = Chessboard.COLUMN_MIN;
                    column <= Chessboard.COLUMN_MAX; ++column)
                {
                    var cur_cell = new ChessboardCell(row, column);
                    Players player;
                    Pieces piece;
                    if (board.IsPieceAt(cur_cell, out player, out piece))
                    {
                        if (empty_cells != 0)
                        {
                            s.Append(empty_cells);
                            empty_cells = 0;
                        }
                        s.Append(PieceToChar(player, piece));
                    }
                    else
                    {
                        ++empty_cells;
                    }
                }
                if (empty_cells != 0)
                {
                    s.Append(empty_cells);
                    empty_cells = 0;
                }
                if (row != Chessboard.ROW_MIN)
                {
                    s.Append(ROWS_SEPARATOR);
                }
            }

            // Encode player to move
            s.Append(' ');
            s.Append(position.IsWhiteToMove ? FEN_WHITE_TO_MOVE : FEN_BLACK_TO_MOVE);

            // Encode castling availability
            s.Append(' ');
            if (!position.IsCanCastleShort(Players.White)
                && !position.IsCanCastleLong(Players.White)
                && !position.IsCanCastleShort(Players.Black)
                && !position.IsCanCastleLong(Players.Black))
            {
                s.Append(FEN_NO_ONE_CAN_CASTLE);
            }
            else
            {
                if (position.IsCanCastleShort(Players.White))
                {
                    s.Append(FEN_WHITE_CAN_CASTLE_SHORT);
                }
                if (position.IsCanCastleLong(Players.White))
                {
                    s.Append(FEN_WHITE_CAN_CASTLE_LONG);
                }
                if (position.IsCanCastleShort(Players.Black))
                {
                    s.Append(FEN_BLACK_CAN_CASTLE_SHORT);
                }
                if (position.IsCanCastleLong(Players.Black))
                {
                    s.Append(FEN_BLACK_CAN_CASTLE_LONG);
                }
            }

            // Encode en passant caputre possibility
            s.Append(' ');
            int? capture_en_passant_column = position.CaptureEnPassantColumn;
            if (capture_en_passant_column.HasValue)
            {
                var cell = new ChessboardCell(position.IsWhiteToMove ? 5 : 2,
                    capture_en_passant_column.Value);
                s.Append(cell.ToString());
            }
            else
            {
                s.Append(FEN_CANNOT_CAPTURE_EN_PASSANT);
            }

            s.Append(' ').Append(position.HalfmoveClock);

            s.Append(' ').Append(options.HasFlag(EncodingOptions.SetMovesCountToOne)
                ? 1 : position.FullmoveNumber);

            return s.ToString();
        }

        // TODO - add proper exception handling
        public static CreatePositionData Decode(string fen)
        {
            var s = fen.Split(m_fen_separators, StringSplitOptions.RemoveEmptyEntries);
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

            // Excuse case mismatch
            Players player_to_move;
            switch (char.ToLower(s[cur_s_index][0]))
            {
                case FEN_WHITE_TO_MOVE:
                    player_to_move = Players.White;
                    break;
                case FEN_BLACK_TO_MOVE:
                    player_to_move = Players.Black;
                    break;
                default:
                    // TODO
                    throw new Exception();
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

            int? capture_en_passant_column = null;
            var s_en_passant = s[cur_s_index];
            if (s_en_passant.Length == 2 && char.IsLetter(s_en_passant[0])
                && char.IsDigit(s_en_passant[1]))
            {
                int column = char.ToLower(s_en_passant[0]) - 'a';
                if (column < Chessboard.COLUMN_MIN || column > Chessboard.COLUMN_MAX)
                {
                    // TODO
                    throw new Exception();
                }
                int row = s_en_passant[1] - '0';
                if (row < Chessboard.ROW_MIN || row > Chessboard.ROW_MAX)
                {
                    // TODO
                    throw new Exception();
                }
                // We can check if row is ok (i.e. row == 6 if White to move),
                // but let us forgive this
                // We do not want to confuse user with error message when we
                // can possibly go on
                capture_en_passant_column = column;
            }

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

        private static char PieceToChar(Players player, Pieces piece)
        {
            char result;
            switch (piece)
            {
                case Pieces.King:
                    result = 'k';
                    break;
                case Pieces.Queen:
                    result = 'q';
                    break;
                case Pieces.Rook:
                    result = 'r';
                    break;
                case Pieces.Bishop:
                    result = 'b';
                    break;
                case Pieces.Knight:
                    result = 'n';
                    break;
                default:
                    result = 'p';
                    break;
            }
            if (player == Players.White)
            {
                result = char.ToUpper(result);
            }
            return result;
        }

        private static bool CharToPiece(char c, out Players player, out Pieces piece)
        {
            player = char.IsUpper(c) ? Players.White : Players.Black;
            switch (char.ToLower(c))
            {
                case 'k':
                    piece = Pieces.King;
                    break;
                case 'q':
                    piece = Pieces.Queen;
                    break;
                case 'r':
                    piece = Pieces.Rook;
                    break;
                case 'b':
                    piece = Pieces.Bishop;
                    break;
                case 'n':
                    piece = Pieces.Knight;
                    break;
                case 'p':
                    piece = Pieces.Pawn;
                    break;
                default:
                    piece = Pieces.NoPiece;
                    break;
            }
            return piece != Pieces.NoPiece;
        }

        private static readonly char[] m_fen_separators = {
            ROWS_SEPARATOR, ' ', '\t' };

    }
}
     */
}