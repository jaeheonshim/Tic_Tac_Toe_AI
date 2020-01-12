package com.jaeheonshim.tictactoeai;

import java.util.List;
import java.util.Scanner;

public class AI {
    public static Board.CellState ai = Board.CellState.X;
    public static Board.CellState human = Board.CellState.O;

    public static Integer[] getBestMove(Board board) {
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

    public static int minimax(Board board, boolean maximising) {
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
            int bestScore = -100;
            for (Integer[] position : board.getAvailablePositions()) {
                board.place(position, ai);
                int currentScore = minimax(board, false);
                board.place(position, Board.CellState.BLANK);
                if(currentScore > bestScore) {
                    bestScore = currentScore;
                }
            }
            return bestScore;
        } else {
            int bestScore = 100;
            for (Integer[] position : board.getAvailablePositions()) {
                board.place(position, human);
                int currentScore = minimax(board, true);
                board.place(position, Board.CellState.BLANK);
                if(currentScore < bestScore) {
                    bestScore = currentScore;
                }
            }
            return bestScore;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        Scanner in = new Scanner(System.in);
        board.printBoard();
        while(board.checkWon() == null) {
            System.out.print("Enter position (x, y): ");
            String[] position = in.nextLine().split(",");
            Integer[] pos = new Integer[2];
            pos[0] = 2 - Integer.parseInt(position[1].trim());
            pos[1] = Integer.parseInt(position[0].trim());
            board.place(pos, Board.CellState.O);
            board.printBoard();
            System.out.println();
            if(board.checkWon() != null) {
                break;
            }
            System.out.println("AI is playing...");
            board.place(getBestMove(board), Board.CellState.X);
            board.printBoard();
        }

        System.out.println("=======================");
        System.out.println("Game Finished!");
        board.printBoard();
        if(board.checkWon() == ai) {
            System.out.println("AI WON!");
        } else if(board.checkWon() == human) {
            System.out.println("YOU WON?!");
        } else {
            System.out.println("TIE");
        }
    }
}