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
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.printMatch(chessMatch, captured);//Printa o software na tela.
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);//A posi��o retornada pelo m�todo readChessPosition � definida como ponto de origem.
				
				System.out.println();
				System.out.print("Tarjet: ");
				ChessPosition tarjet = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, tarjet);//As posi��es retornadas s�o passadas como par�metro para o m�todo repons�vel por converte-las e realizar o movimento delas.
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if(chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.next().toUpperCase();
					while(!type.equals("Q") && !type.equals("N") && !type.equals("B") && !type.equals("R")) {
						System.out.print("Valid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.next().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
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
		UI.printMatch(chessMatch, captured);
		
	}

}
