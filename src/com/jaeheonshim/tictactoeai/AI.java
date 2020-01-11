package com.jaeheonshim.tictactoeai;

public class AI {
    public static int[] getEmptySpot(Board board) {
        for(int i = 0; i < board.getBoard().length; i++) {
            for(int j = 0; j < board.getBoard()[i].length; j++) {
                if(board.getBoard()[i][j] == Board.CellState.BLANK) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
