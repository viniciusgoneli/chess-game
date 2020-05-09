package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){//Esse método simplesmente retorna a matriz de ChessPieces.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); 
			}
		}
		return mat;
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {//Esse é o método responsável por executar o movimento de alguma peça e retornar uma peça capturada, caso exista. 
		Position source = sourcePosition.toPosition();//A variável source recebe a posiçao de xadrez convertida para posição de matriz.
		Position tarjet = targetPosition.toPosition();
		validateSourcePosition(source);//Esse método verifica a existência de uma peça em dada posição.
		validateTarjetPosition(source, tarjet);
		Piece capturedPiece = makeMove(source, tarjet);//A peça capturada recebe o valor retornado pelo método que faz o movimento.
		return (ChessPiece)capturedPiece;//A peça retornada é convertida para o tipo ChessPiece através de um downcasting.
	}
	
	private Piece makeMove(Position source, Position tarjet) {//Esse é o método responsável por realizar o movimento. É um extensão do método acima.
		Piece p = board.removePiece(source);//A peça 'p' é removida da posição de origem.
		Piece capturedPiece = board.removePiece(tarjet);//A peça que está na posição de destino é removida.
		board.placePiece(p, tarjet);//A peça 'p' que estava na posição de origem é colocada na posição de destino.
		return capturedPiece;//O método retorna a peça que estava na posição de destino como uma peça capturada.
	}
	
	public void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece in source position!");
		}
		if(!board.piece(position).isThereanyPossibleMoves()) {//Esse método lança uma excessão caso a peça de dada posição não possua movimentos disponíveis no tabuleiro.
			throw new ChessException("There's no possible moves for the chosen pieces.");
		}
	}
	
	private void validateTarjetPosition(Position source,Position tarjet) {
		if(!board.piece(source).possibleMove(tarjet)) {
			throw new ChessException("The chosen piece can't move to tarjet position!");
		}
	}
	
	private void placeNewPiece(char column,int row,ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
