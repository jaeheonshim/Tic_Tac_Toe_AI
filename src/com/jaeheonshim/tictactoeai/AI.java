package com.jaeheonshim.tictactoeai;

public class AI {
	 public static Board.CellState ai = Board.CellState.X;
	 public static Board.CellState human = Board.CellState.O;

	 public static Integer[] getBestMove (Board board) {
		  Integer[] bestMove = null;
		  int bestScore = -100;
		  for (Integer[] position : board.getAvailablePositions()) {
				board.place(position, ai);
				int score = minimax(board);
				board.place(position, Board.CellState.BLANK);
				if (score > bestScore) {
					 bestScore = score;
					 bestMove = position;
				}
		  }
		  return bestMove;
	 }

	 public static int minimax (Board board) {
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

		  int aiCount = 0;
		  int humanCount = 0;

		  for (int i = 0; i < board.getBoard().length; i++) {
				for (int j = 0; j < board.getBoard()[i].length; j++) {
					 if (board.getBoard()[i][j] == ai) {
						  aiCount++;
					 } else if (board.getBoard()[i][j] == human) {
						  humanCount++;
					 }
				}
		  }

		  int bestScore;
		  if (humanCount > aiCount) {
				bestScore = -1;
		  } else {
				bestScore = 1;
		  }

		  for (Integer[] position : board.getAvailablePositions()) {
				board.place(position, humanCount > aiCount ? ai : human);
				int currentScore = minimax(board);
				board.place(position, Board.CellState.BLANK);
				if (humanCount > aiCount ? currentScore > bestScore : currentScore < bestScore) {
					 bestScore = currentScore;
				}
		  }
		  return bestScore;
	 }
}
