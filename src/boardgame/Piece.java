package boardgame;

public abstract class Piece {//Essa classe representa as pe�as da camada de tabuleiro e � a superclasse das demais.
	
	protected Position position;//Toda pe�a tem uma posi��o. Como essa classe � da camda board, a position precisa ser tamb�m.
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return this.board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereanyPossibleMoves() {//Esse m�todo verifica se existem movimentos poss�veis para data pe�a.
		boolean[][] mat = possibleMoves();
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
