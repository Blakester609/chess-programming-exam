package chess.pieces;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class KingMovesCalculator implements PieceMovesCalculator{
    private final ChessGame.TeamColor pieceColor;
    ArrayList<ChessMove> validMoves = new ArrayList<>();
    private final int[][] possibleMoves = {{1,0}, {1,1}, {0,1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
    public KingMovesCalculator(ChessGame.TeamColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition) {

        for(int[] possibleMove : possibleMoves) {
            int newRow = myPosition.getRow() + possibleMove[0];
            int newCol = myPosition.getColumn() + possibleMove[1];
            if((newRow > 8 || newCol > 8 || newRow < 1 || newCol < 1) || isStuck(newRow, newCol, board, this.pieceColor)) {
                continue;
            }
            ChessPosition newPosition = new ChessPosition(newRow, newCol);
            ChessMove newMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(newMove);
        }
        return validMoves;
    }

    public boolean isStuck(int newRow, int newCol, ChessBoard board, ChessGame.TeamColor pieceColor) {
        if (((newRow <= 8) && (newRow >= 1)) && ((newCol <= 8) && (newCol >= 1))) {
            if(board.getPiece(new ChessPosition(newRow, newCol)) != null){
                return board.getPiece(new ChessPosition(newRow, newCol)).getTeamColor() == pieceColor;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KingMovesCalculator that = (KingMovesCalculator) o;
        return pieceColor == that.pieceColor && Objects.equals(validMoves, that.validMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, validMoves);
    }
}
