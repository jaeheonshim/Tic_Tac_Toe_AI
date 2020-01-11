package com.jaeheonshim.tictactoeai;

public class Board {
    private CellState[][] board = new CellState[][] {
            {CellState.BLANK, CellState.BLANK, CellState.BLANK},
            {CellState.BLANK,CellState.BLANK, CellState.BLANK},
            {CellState.BLANK, CellState.BLANK, CellState.BLANK}
    };

    public CellState[][] getBoard() {
        return board;
    }

    public boolean place(int[] location, CellState player) {
        if(board[location[1]][location[0]] == CellState.BLANK) {
            board[location[1]][location[0]] = player;
            return true;
        } else {
            return false;
        }
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != CellState.BLANK) {
                    System.out.print(board[i][j] + (j + 1 == board[i].length ? "" : "|"));
                } else {
                    System.out.print(" " + (j + 1 == board[i].length ? "" : "|"));
                }
            }
            System.out.print(i + 1 == board.length ? "" : "\n-----\n");
        }
        System.out.println();
    }

    public CellState checkWon() {
        CellState checkingPlayer;

        //check columns
        for(int i = 0; i < board.length; i++) {
            checkingPlayer = board[0][i];
            if(checkingPlayer == CellState.BLANK) {
                checkingPlayer = null;
                continue;
            }
            for(int j = 0; j < board[i].length; j++) {
                if(board[j][i] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if(checkingPlayer != null) {
                return checkingPlayer;
            }
        }

        //check rows
        for(int i = 0; i < board.length; i++) {
            checkingPlayer = board[i][0];
            if(checkingPlayer == CellState.BLANK) {
                checkingPlayer = null;
                continue;
            }
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != checkingPlayer) {
                    checkingPlayer = null;
                    break;
                }
            }
            if(checkingPlayer != null) {
                return checkingPlayer;
            }
        }

        //check diagonals
        checkingPlayer = board[0][0];
        if(board[1][1] == checkingPlayer && board[2][2] == checkingPlayer && checkingPlayer != CellState.BLANK) {
            return checkingPlayer;
        }

        checkingPlayer = board[2][0];
        if(board[1][1] == checkingPlayer && board[0][2] == checkingPlayer && checkingPlayer != CellState.BLANK) {
            return checkingPlayer;
        }

        return null;
    }

    public enum CellState {
        X, O, BLANK
    }
}
