

public class Grid {
    
    private int rows;
    private int cols;
    private Letter[][] grid;

    public Grid(int rows, int cols) {
        if(rows < 1) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if(cols < 1) {
            throw new IllegalArgumentException("Invalid cols");
        }
        this.rows = rows;
        this.cols = cols;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j] = new Letter();
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public Letter[][] getGrid() {
        return grid;
    }

    public char getLetter(int row, int col) {
        if(rows < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if(cols < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid cols");
        }
        return grid[row][col].getLetter();
    }

    public Letter.Status getStatus(int row, int col) {
        if(rows < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if(cols < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid cols");
        }
        return grid[row][col].getStatus();
    }

    public void updateLetter(int row, int col, char letter, Letter.Status status) {
        if(rows < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if(cols < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid cols");
        }
        grid[row][col].setLetter(letter);
        grid[row][col].setStatus(status);
    }

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
                if(!this.grid[rows][cols].equals(g1.grid[rows][cols])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String total = "";
        for(int i = 0; i < rows; i++) {
            total = total.concat("|");
            for(int j = 0; j < cols; j++) {
                total = total.concat(this.grid[rows][cols].toString() + "|");
            }
            total = total.concat("\n");
        }
        return total;
    }
}