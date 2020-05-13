package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		if(getColor() == Color.WHITE) {
			
			//Basic move.
			p.setValues(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Double move.
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && this.getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Kill opponent.
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//En passant
			Position left = new Position(position.getRow(), position.getColumn() - 1);
			if(isThereOpponentPiece(left) && getBoard().positionExists(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable() && position.getRow() == 3) {
				mat[left.getRow() - 1][left.getColumn()] = true;
			}
			
			Position right = new Position(position.getRow(), position.getColumn() + 1);
			if(isThereOpponentPiece(right) && getBoard().positionExists(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable() && position.getRow() == 3) {
				mat[left.getRow() - 1][left.getColumn()] = true;
			}
			
			
		}else {
			//Basic move.
			p.setValues(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Double move.
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && this.getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Kill opponent.
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//En passant
			Position left = new Position(position.getRow(), position.getColumn() - 1);
			if(isThereOpponentPiece(left) && getBoard().positionExists(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable() && position.getRow() == 4) {
				mat[left.getRow() + 1][left.getColumn()] = true;
			}
			
			Position right = new Position(position.getRow(), position.getColumn() + 1);
			if(isThereOpponentPiece(right) && getBoard().positionExists(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable() && position.getRow() == 4) {
				mat[left.getRow() + 1][left.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
