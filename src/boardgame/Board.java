package boardgame;

import chess.BoardException;

public class Board {//Essa classe controla as interações e funções executadas no tabuleiro.
	
	private int rows;//Rows representa a quantidade de linhas do tabuleiro.
	private int columns;//Columns representa a quantidade de colunas do tabuleiro.
	private Piece[][] pieces;//Essa variável é utilizada para armazenar a posição de determinada peça no tabuleiro.
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row,int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board.");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {//Esse é o método responsável em retornar uma peça numa dada posição.
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {//Esse é o método responsável por definir a posição de determinada peça e colocá-la no tabuleiro.
		if(thereIsAPiece(position)) {
			throw new BoardException("There's already a piece on position "+position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		if(piece(position) == null) {
			return null;
		}else {
			Piece aux = piece(position);//Uma variável auxiliar recebe o valor retornado pelo método piece em dada posição.
			aux.position = null;//A posiçao da peça atribuída a var aux recebe valor nulo.
			pieces[position.getRow()][position.getColumn()] = null;
			return aux;
		}
	}
	
	private boolean positionExists(int row,int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board.");
		}
		return piece(position) != null;
	}

}
