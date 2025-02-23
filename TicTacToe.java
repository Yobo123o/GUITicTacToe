/**
 * TicTacToe handles the game logic without GUI elements.
 */
public class TicTacToe {
    private static final int SIZE = 3;
    private String[][] board;
    private String currentPlayer;
    private int moveCount;

    public TicTacToe() {
        board = new String[SIZE][SIZE];
        resetGame();
    }

    public void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = " ";
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            moveCount++;
        }
    }

    public boolean isWin() {
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    private boolean isRowWin() {
        for (int row = 0; row < SIZE; row++) {
            if (!board[row][0].equals(" ") &&
                    board[row][0].equals(board[row][1]) &&
                    board[row][1].equals(board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin() {
        for (int col = 0; col < SIZE; col++) {
            if (!board[0][col].equals(" ") &&
                    board[0][col].equals(board[1][col]) &&
                    board[1][col].equals(board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin() {
        return (!board[0][0].equals(" ") &&
                board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2])) ||
                (!board[0][2].equals(" ") &&
                        board[0][2].equals(board[1][1]) &&
                        board[1][1].equals(board[2][0]));
    }

    public boolean isTie() {
        if (moveCount < 5) return false; // Can't be a tie early in the game

        boolean xFlag, oFlag;
        for (int row = 0; row < SIZE; row++) {
            xFlag = oFlag = false;
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col].equals("X")) xFlag = true;
                if (board[row][col].equals("O")) oFlag = true;
            }
            if (!(xFlag && oFlag)) return false; // A win is still possible
        }

        for (int col = 0; col < SIZE; col++) {
            xFlag = oFlag = false;
            for (int row = 0; row < SIZE; row++) {
                if (board[row][col].equals("X")) xFlag = true;
                if (board[row][col].equals("O")) oFlag = true;
            }
            if (!(xFlag && oFlag)) return false; // A win is still possible
        }

        return true; // No possible wins remaining
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }
}
