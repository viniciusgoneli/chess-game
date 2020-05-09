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
	
	public ChessPiece[][] getPieces(){//Esse m�todo simplesmente retorna a matriz de ChessPieces.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); 
			}
		}
		return mat;
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {//Esse � o m�todo respons�vel por executar o movimento de alguma pe�a e retornar uma pe�a capturada, caso exista. 
		Position source = sourcePosition.toPosition();//A vari�vel source recebe a posi�ao de xadrez convertida para posi��o de matriz.
		Position tarjet = targetPosition.toPosition();
		validateSourcePosition(source);//Esse m�todo verifica a exist�ncia de uma pe�a em dada posi��o.
		validateTarjetPosition(source, tarjet);
		Piece capturedPiece = makeMove(source, tarjet);//A pe�a capturada recebe o valor retornado pelo m�todo que faz o movimento.
		return (ChessPiece)capturedPiece;//A pe�a retornada � convertida para o tipo ChessPiece atrav�s de um downcasting.
	}
	
	private Piece makeMove(Position source, Position tarjet) {//Esse � o m�todo respons�vel por realizar o movimento. � um extens�o do m�todo acima.
		Piece p = board.removePiece(source);//A pe�a 'p' � removida da posi��o de origem.
		Piece capturedPiece = board.removePiece(tarjet);//A pe�a que est� na posi��o de destino � removida.
		board.placePiece(p, tarjet);//A pe�a 'p' que estava na posi��o de origem � colocada na posi��o de destino.
		return capturedPiece;//O m�todo retorna a pe�a que estava na posi��o de destino como uma pe�a capturada.
	}
	
	public void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece in source position!");
		}
		if(!board.piece(position).isThereanyPossibleMoves()) {//Esse m�todo lan�a uma excess�o caso a pe�a de dada posi��o n�o possua movimentos dispon�veis no tabuleiro.
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
