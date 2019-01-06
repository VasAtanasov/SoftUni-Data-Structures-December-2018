import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ex07DistanceInLabyrinth {
    private static BufferedReader reader;
    private static Cell[][] labyrinth;
    private static int size;
    private static Cell start;
    private static LinkedList<Cell> queue;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        initializeLabyrinth();

        queue = new LinkedList<>();
        queue.add(start);

        while (! queue.isEmpty()) {
            Cell cell = queue.removeFirst();

            List<Cell> possibleMoves = new ArrayList<>();
            possibleMoves.add(new Cell(cell.getValue() + 1, cell.getRow() - 1, cell.getCol()));
            possibleMoves.add(new Cell(cell.getValue() + 1, cell.getRow() + 1, cell.getCol()));
            possibleMoves.add(new Cell(cell.getValue() + 1, cell.getRow(), cell.getCol() - 1));
            possibleMoves.add(new Cell(cell.getValue() + 1, cell.getRow(), cell.getCol() + 1));

            for (Cell possibleMove : possibleMoves) {
                if (isValidMove(possibleMove)) {
                    queue.add(possibleMove);
                    labyrinth[possibleMove.getRow()][possibleMove.getCol()] = possibleMove;
                }
            }
        }

        printLabyrinth();


    }

    private static void printLabyrinth() {
        labyrinth[start.getRow()][start.getCol()].setValue(- 2);
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < labyrinth.length; row++) {
            StringBuilder rowString = new StringBuilder();
            for (int col = 0; col < labyrinth[row].length; col++) {
                String symbol = getSymbol(labyrinth[row][col]);
                rowString.append(symbol);
            }
            output.append(rowString).append(System.lineSeparator());
        }
        System.out.println(output.toString());
    }

    private static String getSymbol(Cell cell) {
        String symbol;
        switch (cell.getValue()) {
            case 0:
                symbol = "u";
                break;
            case - 1:
                symbol = "x";
                break;
            case - 2:
                symbol = "*";
                break;
            default:
                symbol = cell.toString();
                break;
        }
        return symbol;
    }

    private static boolean isValidMove(Cell possibleMove) {
        int row = possibleMove.getRow();
        int col = possibleMove.getCol();
        return isValidIndex(row) && isValidIndex(col) && labyrinth[row][col].getValue() == 0;
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    private static void initializeLabyrinth() throws IOException {
        size = Integer.parseInt(reader.readLine());
        labyrinth = new Cell[size][];
        for (int row = 0; row < size; row++) {
            String[] input = reader.readLine().split("");
            labyrinth[row] = new Cell[input.length];
            for (int col = 0; col < input.length; col++) {
                if ("*".equals(input[col])) {
                    start = new Cell(0, row, col);
                    labyrinth[row][col] = start;
                } else {
                    int value = "x".equals(input[col]) ? - 1 : 0;
                    labyrinth[row][col] = new Cell(value, row, col);
                }
            }
        }
    }
}

class Cell {
    private int value;
    private int row;
    private int col;

    Cell(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        return String.format("%d", this.getValue());
    }
}