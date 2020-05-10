package boardgame;

public abstract class Piece {//Essa classe representa as peças da camada de tabuleiro e é a superclasse das demais.
	
	protected Position position;//Toda peça tem uma posição. Como essa classe é da camda board, a position precisa ser também.
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
	
	public boolean isThereanyPossibleMoves() {//Esse método verifica se existem movimentos possíveis para data peça.
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
