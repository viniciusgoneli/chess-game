package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List <ChessPiece> captured = new ArrayList<>();
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
				UI.printMatch(chessMatch, captured);//Printa o software na tela.
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);//A posição retornada pelo método readChessPosition é definida como ponto de origem.
				
				System.out.println();
				System.out.print("Tarjet: ");
				ChessPosition tarjet = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, tarjet);//As posições retornadas são passadas como parâmetro para o método reponsável por converte-las e realizar o movimento delas.
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
	}

}
