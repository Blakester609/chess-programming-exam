package chess.pieces;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class QueenMovesCalculator extends BishopMovesCalculator implements PieceMovesCalculator{
    private final ChessGame.TeamColor pieceColor;
    ArrayList<ChessMove> validMoves = new ArrayList<>();
    public QueenMovesCalculator(ChessGame.TeamColor pieceColor) {
        super(pieceColor);
        this.pieceColor = pieceColor;
    }

    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition) {
        int newRow = myPosition.getRow();
        int newCol = myPosition.getColumn();
        validMoves = (ArrayList<ChessMove>) new BishopMovesCalculator(this.pieceColor).getPieceMoves(board, myPosition);
        while((newRow < 8)) {
            ChessPosition newPosition = new ChessPosition(newRow += 1, newCol);
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
        while((newRow > 1)) {
            ChessPosition newPosition = new ChessPosition(newRow -= 1, newCol);
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
        while((newCol < 8)) {
            ChessPosition newPosition = new ChessPosition(newRow, newCol += 1);
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
        while((newCol > 1)) {
            ChessPosition newPosition = new ChessPosition(newRow, newCol -= 1);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueenMovesCalculator that = (QueenMovesCalculator) o;
        return pieceColor == that.pieceColor && Objects.equals(validMoves, that.validMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, validMoves);
    }
}
