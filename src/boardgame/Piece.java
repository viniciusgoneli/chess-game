package boardgame;

public class Piece {//Essa classe representa as peças da camada de tabuleiro e é a superclasse das demais.
	
	protected Position position;//Toda peça tem uma posição. Como essa classe é da camda board, a position precisa ser também.
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
}
