package chess.pieces;

import chess.*;

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
        if (this.pieceColor == ChessGame.TeamColor.WHITE) {
            moveForward(1, myPosition, board, 2, 8);
        } else if(this.pieceColor == ChessGame.TeamColor.BLACK) {
            moveForward(-1, myPosition, board, 7, 1);
        }

        return validMoves;
    }



    private void moveForward(int forwardIncrement, ChessPosition myPosition, ChessBoard board, int startSquare, int promoteSquare) {
        int newRow = myPosition.getRow() + forwardIncrement;
        int newCol = myPosition.getColumn();
        if(validateCanCapture(newRow, newCol - 1, board, this.pieceColor )) {
            ChessPosition newPosition = new ChessPosition(newRow, newCol - 1);
            if (newRow == promoteSquare) {
                addPromotionMoves(newRow, newCol - 1, myPosition, newPosition);
            } else {
                ChessMove newMove = new ChessMove(myPosition, newPosition, null);
                validMoves.add(newMove);
            }
        }
        if(validateCanCapture(newRow, newCol + 1, board, this.pieceColor )) {
            ChessPosition newPosition = new ChessPosition(newRow, newCol + 1);
            if(newRow == promoteSquare) {
                addPromotionMoves(newRow, newCol + 1, myPosition, newPosition);
            } else {
                ChessMove newMove = new ChessMove(myPosition, newPosition, null);
                validMoves.add(newMove);
            }
        }
        if (validateForwardMove(newRow, newCol, board)) {
            ChessPosition newPosition = new ChessPosition(newRow, newCol);
            if(newRow == promoteSquare) {
                addPromotionMoves(newRow, newCol, myPosition, newPosition);
            } else {
                ChessMove newMove = new ChessMove(myPosition, newPosition, null);
                validMoves.add(newMove);
            }
        }
        if(myPosition.getRow() == startSquare) {
            int jumpRow = myPosition.getRow() + (2 * forwardIncrement);
            if(validateForwardMove(newRow, newCol, board) && validateForwardMove(jumpRow, newCol, board)) {
                ChessPosition newPosition = new ChessPosition(jumpRow, newCol);
                ChessMove newMove = new ChessMove(myPosition, newPosition, null);
                validMoves.add(newMove);
            }
        }
    }



    private void addPromotionMoves(int newRow, int newCol, ChessPosition myPosition, ChessPosition newPosition) {
        validMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.QUEEN));
        validMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK));
        validMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.BISHOP));
        validMoves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KNIGHT));
    }


    private boolean validateForwardMove(int newRow, int newCol, ChessBoard board) {
        if ((((newRow <= 8) && (newRow >= 1)) && ((newCol <= 8) && (newCol >= 1)))) {
            return board.getPiece(new ChessPosition(newRow, newCol)) == null;
        }
        return false;
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
