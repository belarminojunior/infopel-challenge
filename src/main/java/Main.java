import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("WELCOME TO \"Mines Field\"");

        System.out.println("--Table Dimension--");
        int rows = validateSize("Rows: ", 2, Integer.MAX_VALUE);
        int columns = validateSize("Columns: ", 2, Integer.MAX_VALUE);

        int numberOfMines = (int)(Math.random() * (Math.sqrt(rows * columns)) + 1);
        System.out.println(numberOfMines + " Mines in the Field");

        MineField game = new MineField(rows, columns, numberOfMines);
        boolean isPlaying = true;

        while (isPlaying) {
            game.showBoard();
            System.out.print("Enter row and column to reveal (e.g., 1 1): ");
            int row = validateSize("\nRow To Reveal: ", 0, rows + 1) - 1;
            int col = validateSize("Column to Reveal: ", 0, columns + 1) - 1;

            boolean safe = game.revealCell(row, col);
            if (!safe) {
                System.out.println("You hit a mine! Game over.");
                game.showBoard();
                isPlaying = false;
            }
        }
    }
    static int validateSize(String msg, int min, int max) {
        int inputNumber = 0;

        do {
            System.out.print(msg);
            try {
                inputNumber = Integer.parseInt(reader.readLine());

                if (inputNumber < min || inputNumber > max)
                    System.out.println("Invalid Size, Try Again.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (inputNumber < min || inputNumber > max);

        return inputNumber;
    }

}



