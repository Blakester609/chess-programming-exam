package chess.pieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public interface PieceMovesCalculator  {

    Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition);
}
