/**
 * Model for Conway's Game of Life.
 *
 * This class stores the society in a 2D boolean array.
 * true  = live cell
 * false = empty location
 *
 * IMPORTANT FOR THIS PROJECT:
 * The board does NOT wrap around. Any location outside the array is simply
 * ignored when counting neighbors.
 */
public class GameOfLife {

    
    private boolean[][] society;

    /**
     * Creates an empty society with the requested number of rows and columns.
     */
    public GameOfLife(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        society = new boolean[rows][cols];
    }

    /** Returns the number of rows in the society. */
    public int numberOfRows() {
        return society.length;
    }

    /** Returns the number of columns in the society. */
    public int numberOfColumns() {
        return society[0].length;
    }

    /** Makes the location at row, col alive. */
    public void growCellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            society[row][col] = true;
        }
    }

    /** Makes the location at row, col dead. */
    public void killCellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            society[row][col] = false;
        }
    }

    /** Returns true if the location contains a live cell. */
    public boolean cellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            return society[row][col];
        }
        return false;
    }

    /** Makes every location in the society dead. */
    public void clear() {
        for (int i = 0; i < society.length; i++) {
            for (int j = 0; j < society[0].length; j++) {
                society[i][j] = false;
            }
        }
    }

    /**
     * Counts the live neighbors surrounding one location.
     *
     * A location can have at most eight neighbors. Locations outside the
     * board DO NOT wrap around to the other side.
     */
    public int neighborCount(int row, int col) {
        int count = 0;
        
        for(int i = row - 1; i <= row + 1; i++) {
            for(int j = col - 1; j <= col + 1; j++) {
                if(i == row && j == col) {
                    continue; // Skip the cell itself
                }
                if(i >= 0 && i < society.length && j >= 0 && j < society[0].length) {
                    if(society[i][j]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * Advances the entire society by one generation.
     *
     * Rules:
     * 1. A dead cell with exactly 3 live neighbors becomes alive.
     * 2. A live cell with 2 or 3 live neighbors survives.
     * 3. A live cell with fewer than 2 neighbors dies from isolation.
     * 4. A live cell with more than 3 neighbors dies from overpopulation.
     *
     */
    public void update() {
        boolean[][] nextGeneration = new boolean[society.length][society[0].length];

        for (int i = 0; i < society.length; i++) {
            for (int j = 0; j < society[0].length; j++) {
                int neighbors = neighborCount(i, j);
                if (society[i][j]) { // Cell is alive
                    nextGeneration[i][j] = (neighbors == 2 || neighbors == 3);
                } else { // Cell is dead
                    nextGeneration[i][j] = (neighbors == 3);
                }
            }
        }
        society = nextGeneration;

    }

    /**
     * Returns a text version of the board.
     * O = live cell
     * . = dead cell
     *
     */
    @Override
    public String toString() {
        //       Add a newline after every row.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < society.length; i++) {
            for (int j = 0; j < society[0].length; j++) {
                if (society[i][j]) {
                    sb.append("O");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
