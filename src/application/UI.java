package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import boardgame.Piece;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

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
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPiece(captured);
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
	
	private static void printCapturedPiece(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces: ");
		System.out.print("White: ");
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print("Black: ");
		System.out.println(Arrays.toString(black.toArray()));
	}
}
