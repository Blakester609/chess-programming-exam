package chess.pieces;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BishopMovesCalculator implements PieceMovesCalculator {
    private final ChessGame.TeamColor pieceColor;
    ArrayList<ChessMove> validMoves = new ArrayList<>();
    public BishopMovesCalculator(ChessGame.TeamColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BishopMovesCalculator that = (BishopMovesCalculator) o;
        return pieceColor == that.pieceColor && Objects.equals(validMoves, that.validMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, validMoves);
    }

    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition) {
        int newRow = myPosition.getRow();
        int newCol = myPosition.getColumn();
        while((newRow < 8) && (newCol < 8)) {
            ChessPosition newPosition = new ChessPosition(newRow += 1, newCol += 1);
            if(isStuck(newRow, newCol, board, this.pieceColor)) {
                break;
            }
            ChessMove newMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(newMove);
            if(validateCanCapture(newRow, newCol, board, this.pieceColor)) {
                break;
            }
        }
        newRow = myPosition.getRow();
        newCol = myPosition.getColumn();
        while((newRow > 1) && (newCol < 8)) {
            ChessPosition newPosition = new ChessPosition(newRow -= 1, newCol += 1);
            if(isStuck(newRow, newCol, board, this.pieceColor)) {
                break;
            }
            ChessMove newMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(newMove);
            if(validateCanCapture(newRow, newCol, board, this.pieceColor)) {
                break;
            }
        }
        newRow = myPosition.getRow();
        newCol = myPosition.getColumn();
        while((newRow > 1) && (newCol > 1)) {
            ChessPosition newPosition = new ChessPosition(newRow -= 1, newCol -= 1);
            if(isStuck(newRow, newCol, board, this.pieceColor)) {
                break;
            }
            ChessMove newMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(newMove);
            if(validateCanCapture(newRow, newCol, board, this.pieceColor)) {
                break;
            }
        }
        newRow = myPosition.getRow();
        newCol = myPosition.getColumn();
        while((newRow < 8) && (newCol > 1)) {
            ChessPosition newPosition = new ChessPosition(newRow += 1, newCol -= 1);
            if(isStuck(newRow, newCol, board, this.pieceColor)) {
                break;
            }
            ChessMove newMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(newMove);
            if(validateCanCapture(newRow, newCol, board, this.pieceColor)) {
                break;
            }
        }
        return validMoves;
    }

    public boolean isStuck(int newRow, int newCol, ChessBoard board, ChessGame.TeamColor pieceColor) {
        if ((((newRow <= 8) && (newRow >= 1)) && ((newCol <= 8) && (newCol >= 1)))) {
            if(board.getPiece(new ChessPosition(newRow, newCol)) != null){
                return board.getPiece(new ChessPosition(newRow, newCol)).getTeamColor() == pieceColor;
            }
        }
        return false;
    }

    public boolean validateCanCapture(int newRow, int newCol, ChessBoard board, ChessGame.TeamColor pieceColor) {
        if ((((newRow <= 8) && (newRow >= 1)) && ((newCol <= 8) && (newCol >= 1))) && board.getPiece(new ChessPosition(newRow, newCol)) != null) {
            return board.getPiece(new ChessPosition(newRow, newCol)).getTeamColor() != pieceColor;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BishopMovesCalculator{" +
                "pieceColor=" + pieceColor +
                ", validMoves=" + validMoves +
                '}';
    }
}
