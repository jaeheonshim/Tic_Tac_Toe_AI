package com.jaeheonshim.tictactoeai;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private CellState[][] board = new CellState[][]{
            {CellState.BLANK, CellState.BLANK, CellState.BLANK},
            {CellState.BLANK, CellState.BLANK, CellState.BLANK},
            {CellState.BLANK, CellState.BLANK, CellState.BLANK}
    };

    public CellState[][] getBoard() {
        return board;
    }

    public void place(Integer[] location, CellState player) {
        board[location[0]][location[1]] = player;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.print(2 - i + " ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != CellState.BLANK) {
                    System.out.print(board[i][j] + (j + 1 == board[i].length ? "" : "|"));
                } else {
                    System.out.print(" " + (j + 1 == board[i].length ? "" : "|"));
                }
            }
            System.out.print(i + 1 == board.length ? "" : "\n  -----\n");
        }
        System.out.println("\n  0 1 2");
        System.out.println();
    }

    public CellState checkWon() {
        CellState checkingPlayer;

        //check columns
        for (int i = 0; i < board.length; i++) {
            checkingPlayer = board[0][i];
            if (checkingPlayer == CellState.BLANK) {
                checkingPlayer = null;
                continue;
            }
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if (checkingPlayer != null) {
                return checkingPlayer;
            }
        }

        //check rows
        for (int i = 0; i < board.length; i++) {
            checkingPlayer = board[i][0];
            if (checkingPlayer == CellState.BLANK) {
                checkingPlayer = null;
                continue;
            }
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if (checkingPlayer != null) {
                return checkingPlayer;
            }
        }

        //check diagonals
        checkingPlayer = board[0][0];
        if (board[1][1] == checkingPlayer && board[2][2] == checkingPlayer && checkingPlayer != CellState.BLANK) {
            return checkingPlayer;
        }

        checkingPlayer = board[2][0];
        if (board[1][1] == checkingPlayer && board[0][2] == checkingPlayer && checkingPlayer != CellState.BLANK) {
            return checkingPlayer;
        }

        //check tie;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == CellState.BLANK) {
                    return null;
                }
            }
        }

        return CellState.BLANK;
    }

    public List<Integer[]> getAvailablePositions() {
        ArrayList<Integer[]> availablePositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Board.CellState.BLANK) {
                    availablePositions.add(new Integer[]{i, j});
                }
            }
        }
        return availablePositions;
    }

    public enum CellState {
        X, O, BLANK
    }
}
