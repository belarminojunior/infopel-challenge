public class MineField {

    private static final int MINE = -1; // The "*"
    int[][] board;
    private final boolean[][] revealed;

    int rows, columns, numOfMines;

    public MineField(int rows, int columns, int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numberOfMines;
        this.board = new int[rows][columns];
        revealed = new boolean[rows][columns];
        placeMines();
        calculateNumbers();
    }

    void placeMines() {
        int counter = 0;
        while (counter != numOfMines) {
            int randRow = (int) (Math.random() * this.rows);
            int randCols = (int) (Math.random() * this.columns);

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

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself

                int newRow = r + i;
                int newCol = c + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns && board[newRow][newCol] == MINE) {
                    count++;
                }
            }
        }
        return count;
    }


    void showBoard() {
        System.out.println("Current Board: ");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (revealed[r][c]) {
                    if (board[r][c] == MINE) {
                        System.out.print(-1);
                    } else {
                        System.out.print(board[r][c] + " \t");
                    }
                } else {
                    System.out.print(". \t");
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
        return board[r][c] != MINE; // Mine was hit
    }
}
