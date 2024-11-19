/**
 * Represents a grid used in the game
 * @author Amelia Saldino
 */
public class Grid {
    /**
     * The number of rows in the grid
     */
    private int rows;

     /**
     * The number of columns in the grid
     */
    private int cols;

    /**
     * A 2D array of objects representing the grid's content
     */
    private Letter[][] grid;


    /**
     * Constructor for grid of specific rows and cols
     * @param rows number of rows
     * @param cols number of cols
     * @throws IllegalArgumentException if rows or cols are less than 1
     */
    public Grid(int rows, int cols) {
        if(rows < 1) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if(cols < 1) {
            throw new IllegalArgumentException("Invalid cols");
        }
        this.rows = rows;
        this.cols = cols;
        grid = new Letter[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j] = new Letter();
            }
        }
    }

    /**
     * Returns the number of rows in the grid
     * @return The number of rows.
     */
    public int getRows() {
        return this.rows;
    }

     /**
     * Returns the number of columns in the grid
     * @return The number of columns.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Returns the entire 2D grid of objects
     * @return A 2D array representing the grid.
     */
    public Letter[][] getGrid() {
        return grid;
    }

    /**
     * Retrieves the letter at the specified row and column in the grid
     * @param row The row index 
     * @param col The column index
     * @return The character stored in the grid at the specified position
     * @throws IllegalArgumentException if the row or column index is out of bounds
     */
    public char getLetter(int row, int col) {
        if(row < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid[row][col].getLetter();
    }

    /**
     * Retrieves the status of the letter at the specified row and column in the grid
     * @param row The row index 
     * @param col The column index
     * @return The status of the letter at the specified position
     * @throws IllegalArgumentException if the row or column index is out of bounds
     */
    public Letter.Status getStatus(int row, int col) {
        if(row < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid[row][col].getStatus();
    }

    /**
     * Updates the letter and its status at the specified row and column in the grid
     * @param row The row index 
     * @param col The column index 
     * @param letter The letter to set at the specified position
     * @param status The status to set for the letter at the specified position
     * @throws IllegalArgumentException if the row or column index is out of bounds
     */
    public void updateLetter(int row, int col, char letter, Letter.Status status) {
        if(row < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if(col < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        grid[row][col].setLetter(letter);
        grid[row][col].setStatus(status);
    }

     /**
     * Compares this grid object to another object for equality. 
     * Two grids are considered equal if they have the same number of rows, 
     * the same number of columns, and if each corresponding object in the grid is equal
     * @param o The object to compare this grid to
     * @return true if the grids are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Grid g1 = (Grid)o;
        if(this.rows != g1.rows || this.cols != g1.cols) {
            return false;
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(!this.grid[i][j].equals(g1.grid[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the grid, where each row of the grid is
     *  represented as a string with the objects' method called, 
     * and the rows are separated by newlines
     * @return A string representation of the grid
     */
    @Override
    public String toString() {
        String total = "";
        for(int i = 0; i < rows; i++) {
            total = total.concat("|");
            for(int j = 0; j < cols; j++) {
                total = total.concat(this.grid[i][j].toString() + "|");
            }
            total = total.concat("\n");
        }
        return total;
    }
}

