package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn = 0;
	private Color currentPlayer;
	private Board board;
	private List<Piece> _piecesOnTheBoard, _capturedPieces;
	private boolean check;
	private boolean checkMate;

	public ChessMatch() {
		board = new Board(8, 8);
		_piecesOnTheBoard = new ArrayList<>();
		_capturedPieces = new ArrayList<>();
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public boolean getCheck() {
		return this.check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}

	public ChessPiece[][] getPieces() {// Esse método simplesmente retorna a matriz de ChessPieces.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	private void nextTurn() {// Esse método aumenta o turno e muda o valor da peça atual.
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {																// exista.
		Position source = sourcePosition.toPosition();
		Position tarjet = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTarjetPosition(source, tarjet);
		Piece capturedPiece = makeMove(source, tarjet);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, tarjet, capturedPiece);
			throw new ChessException("You cannot put your king in check!");	
		}
		
		check = (testCheck(Opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(Opponent(currentPlayer))) {
			checkMate = true;
		}else {
			nextTurn();
		}

		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position tarjet) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(tarjet);
		board.placePiece(p, tarjet);
		if (capturedPiece != null) {
			_piecesOnTheBoard.remove(capturedPiece);
			_capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position tarjet, Piece capturedPiece) {
		Piece p = board.removePiece(tarjet);
		board.placePiece(p, source);
		if (capturedPiece != null) {
			_capturedPieces.remove(capturedPiece);
			board.placePiece(capturedPiece, tarjet);
			_piecesOnTheBoard.add(capturedPiece);
		}

	}

	public void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece in source position!");
		}
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours.");
		}
		if (!board.piece(position).isThereanyPossibleMoves()) {// Esse método lança uma excessão caso a peça de dada
																// posição não possua movimentos disponíveis no
																// tabuleiro.
			throw new ChessException("There's no possible moves for the chosen pieces.");
		}
	}

	private void validateTarjetPosition(Position source, Position tarjet) {
		if (!board.piece(source).possibleMove(tarjet)) {
			throw new ChessException("The chosen piece can't move to tarjet position!");
		}

	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		_piecesOnTheBoard.add(piece);
	}

	private Color Opponent(Color color) {
		if (color == Color.BLACK) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	private ChessPiece king(Color color) {
		for (int i = 0; i < _piecesOnTheBoard.size(); i++) {
			ChessPiece e = (ChessPiece) _piecesOnTheBoard.get(i);
			if (e instanceof King && e.getColor() == color) {
				return e;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board.");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = _piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == Opponent(color)).collect(Collectors.toList());
		for (Piece e : opponentPieces) {
			boolean mat[][] = e.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List<Piece> list = _piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0;i<board.getRows();i++) {
				for(int j=0;j<board.getColumns();j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));

		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
}
