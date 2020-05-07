package boardgame;

public class Piece {//Essa classe representa as pe�as da camada de tabuleiro e � a superclasse das demais.
	
	protected Position position;//Toda pe�a tem uma posi��o. Como essa classe � da camda board, a position precisa ser tamb�m.
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
}
