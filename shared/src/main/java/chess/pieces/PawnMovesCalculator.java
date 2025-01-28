package chess.pieces;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class PawnMovesCalculator implements PieceMovesCalculator{
    private final ChessGame.TeamColor pieceColor;
    ArrayList<ChessMove> validMoves = new ArrayList<>();
    public PawnMovesCalculator(ChessGame.TeamColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition) {
        return validMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PawnMovesCalculator that = (PawnMovesCalculator) o;
        return pieceColor == that.pieceColor && Objects.equals(validMoves, that.validMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, validMoves);
    }
}
