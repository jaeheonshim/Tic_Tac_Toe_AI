package com.jaeheonshim.tictactoeai;

import java.util.Scanner;

import static com.jaeheonshim.tictactoeai.AI.*;

public class Main {
	 public static void main (String[] args) {
		  Board board = new Board();
		  Scanner in = new Scanner(System.in);
		  board.printBoard();
		  while (board.checkWon() == null) {
				Integer[] pos;
				boolean inputValid;
				do {
					 System.out.print("Enter position (x, y): ");
					 inputValid = true;
					 String[] position = in.nextLine().split(",");
					 pos = new Integer[2];
					 if(position.length < 2) {
					 	 inputValid = false;
					 	 continue;
					 }
					 try {
						  pos[0] = 2 - Integer.parseInt(position[1].trim());
						  pos[1] = Integer.parseInt(position[0].trim());
					 } catch (NumberFormatException e) {
					 	 inputValid = false;
					 }
					 if(board.getBoard()[pos[0]][pos[1]] != Board.CellState.BLANK) {
					 	 inputValid = false;
					 }
				} while(!inputValid);

				board.place(pos, Board.CellState.O);

				board.printBoard();
				System.out.println();
				if (board.checkWon() != null) {
					 break;
				}
				System.out.println("AI is playing...");
				board.place(getBestMove(board), Board.CellState.X);
				board.printBoard();
		  }

		  System.out.println("=======================");
		  System.out.println("Game Finished!");
		  board.printBoard();
		  if (board.checkWon() == ai) {
				System.out.println("AI WON!");
		  } else if (board.checkWon() == human) {
				System.out.println("YOU WON?!");
		  } else {
				System.out.println("TIE");
		  }
	 }
}
