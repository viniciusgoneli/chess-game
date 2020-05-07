package boardgame;

public class Position {//Essa classe representa as posi��es da camada de tabuleiro.
	
	private int row;//Row representa a linha da posi��o da camda de tabuleiro, que � a mesma de matriz.
	private int column;//Column representa a coluna da posi��o da camda de tabuleiro, que � a mesma de matriz.
	
	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return row +", "+column;
	}

}
