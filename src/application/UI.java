package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class UI {
	
	
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();//A var 's' aguarda o valor digitado pelo usuário.
			char column = s.charAt(0);//O primeiro caracter do texto digitado é dado como a coluna do tabuleiro.
			int row = Integer.parseInt(s.substring(1));//O segundo caracter em diante é dado como a linha do tabuleiro. 
			return new ChessPosition(column, row);//O método retorna a nova posição no formato de xadrez.
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}
	
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Turn: "+chessMatch.getTurn());
		System.out.println("Waiting player: "+chessMatch.getCurrentPlayer());
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0;i<pieces.length;i++) {
			System.out.print((8 - i) + " ");
			for(int j=0;j<pieces.length;j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
