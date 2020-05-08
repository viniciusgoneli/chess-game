package boardgame;

public class Position {//Essa classe representa as posições da camada de tabuleiro.
	
	private int row;//Row representa a linha da posição da camda de tabuleiro, que é a mesma de matriz.
	private int column;//Column representa a coluna da posição da camda de tabuleiro, que é a mesma de matriz.
	
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
	
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setValues(int row,int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return row +", "+column;
	}

}
