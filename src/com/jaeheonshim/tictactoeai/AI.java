package com.jaeheonshim.tictactoeai;

import java.util.List;
import java.util.Scanner;

public class AI {
	 public static Board.CellState ai = Board.CellState.X;
	 public static Board.CellState human = Board.CellState.O;

	 public static Integer[] getBestMove (Board board) {
		  Integer[] bestMove = null;
		  int bestScore = -100;
		  for (Integer[] position : board.getAvailablePositions()) {
				board.place(position, ai);
				int score = minimax(board, false);
				board.place(position, Board.CellState.BLANK);
				if (score > bestScore) {
					 bestScore = score;
					 bestMove = position;
				}
		  }
		  return bestMove;
	 }

	 public static int minimax (Board board, boolean maximising) {
		  Board.CellState winner = board.checkWon();
		  int score;
		  if (winner != null) {
				if (winner == ai) {
					 score = 1;
					 return score;
				} else if (winner == Board.CellState.BLANK) {
					 score = 0;
					 return score;
				} else {
					 score = -1;
					 return score;
				}
		  }
		  if (maximising) {
				int bestScore = -1;
				for (Integer[] position : board.getAvailablePositions()) {
					 board.place(position, ai);
					 int currentScore = minimax(board, false);
					 board.place(position, Board.CellState.BLANK);
					 if (currentScore > bestScore) {
						  bestScore = currentScore;
					 }
				}
				return bestScore;
		  } else {
				int bestScore = 1; // calling it best score is confusing because we want this to be the lowest score to see the worst possible scenario
				for (Integer[] position : board.getAvailablePositions()) {
					 board.place(position, human);
					 int currentScore = minimax(board, true);
					 board.place(position, Board.CellState.BLANK);
					 if (currentScore < bestScore) {
						  bestScore = currentScore;
					 }
				}
				return bestScore;
		  }
	 }
}
