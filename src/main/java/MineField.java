import java.util.Arrays;

public class MineField {

    private static final int MINE = -1; // The "*"
    int[][] board;
    private boolean[][] revealed;

    int rows, columns, numOfMines;

    public MineField(int rows, int columns, int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numberOfMines;
        this.board = new int[rows][columns];
        revealed = new boolean[rows][columns];
        placeMines();
        calculateNumbers();
        printBoard();
    }

    void printBoard() {
        for (int[] lines : board) {
            System.out.println(Arrays.toString(lines));
        }
    }

    void placeMines() {
        int counter = 0;
        while (counter != numOfMines) {
            int randRow = (int)(Math.random() * this.rows);
            int randCols = (int)(Math.random() * this.columns);

            if (board[randRow][randCols] != MINE) {
                this.board[randRow][randCols] = MINE;
                counter++;
            }
        }
    }

    void calculateNumbers() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (board[r][c] == MINE) {
                    continue;
                }

                board[r][c] = countAdjacentMines(r, c);
            }
        }
    }
    
    int countAdjacentMines(int r, int c) {
        int count = 0;

        // Same row
        for (int col = 0; col < columns; col++) {
            if (col != c && board[r][col] == MINE) {
                count++;
            }
        }

        // Same column
        for (int row = 0; row < rows; row++) {
            if (row != r && board[row][c] == MINE) {
                count++;
            }
        }

        // Main Diagonal (top-left to bottom-right)
        for (int i = -Math.min(r, c); i < Math.min(rows - r, columns - c); i++) {
            if (i != 0 && board[r + i][c + i] == MINE) {
                count++;
            }
        }

        // !Main Diagonal (top-right to bottom-left)
        for (int i = -Math.min(r, columns - c - 1); i < Math.min(rows - r, c + 1); i++) {
            if (i != 0 && board[r + i][c - i] == MINE) {
                count++;
            }
        }

        return count;
    }

    void showBoard() {
        System.out.println("Current Board:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (revealed[r][c]) {
                    if (board[r][c] == MINE) {
                        System.out.print(-1);
                    } else {
                        System.out.print(board[r][c] + " ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public boolean revealCell(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns || revealed[r][c]) {
            return true;
        }

        revealed[r][c] = true;

        if (board[r][c] == MINE) {
            return false;  // Mine was hit
        } else if (board[r][c] == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(r + i, c + j);
                }
            }
        }
        return true;
    }



}
