/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Andrey
 */
public class Life implements LifeInterface {
    private final int SIZE = 50; // set the size of the grid here
    public int[][] grid = new int[SIZE][SIZE];
    private int liveNeighbours;
    private int status;
    private final char EMPTY = 9633;
    private final char FILLED = 9632;

    @Override
    public int countNeighbours(int cellRow, int cellCol) {
        liveNeighbours = 0;
        for (int i = cellRow - 1; i <= cellRow + 1; i++) {
            if (i >= 0 && i < grid.length) {
                for (int j = cellCol - 1; j <= cellCol + 1; j++) {
                    if (j >= 0 && j < grid[i].length) {
                        if (i != cellRow || j != cellCol) {
                            if (grid[i][j] == 1) {
                                liveNeighbours++;
                            }
                        }
                    }
                }
            }
        }
        return liveNeighbours;
    }

    @Override
    public int applyRules(int cellRow, int cellCol) {
        int liveNeighbours = countNeighbours(cellRow, cellCol);

        if (isCellAlive(cellRow, cellCol) && liveNeighbours == 2 || liveNeighbours == 3) {
            status = 1;
        } else {
            status = 0;
        }

        return status;
    }

    @Override
    public void takeStep() {
        int[][] copyArray = new int[grid.length][grid.length];

        for (int[] copyArray1 : copyArray) {
            for (int column = 0; column < copyArray1.length; column++) {
                copyArray1[column] = 0;
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                countNeighbours(row, column);
                copyArray[row][column] = applyRules(row, column);
            }
        }
        //replace old grid with new model
        grid = copyArray.clone();
    }

    public boolean isCellAlive(int cellRow, int cellCol) {
        return grid[cellRow][cellCol] == 1;
    }

    /**
     * puts a random pattern of 1s and 0s on the grid
     * @param grid 
     */
    @Override
    public void setPattern(int[][] grid) {
        int random;
        for (int[] grid1 : grid) {
            for (int j = 0; j < grid1.length; j++) {
                random = (int) (Math.random() * 2);
                grid1[j] = random;
            }
        }
    }

    /**
     * sets all cells to 0
     */
    @Override
    public void killAllCells() {
        for (int[] grid1 : grid) {
            for (int col = 0; col < grid[0].length; col++) {
                grid1[col] = 0;
            }
        }
    }

    /**
     * creates a grid of 0s
     * @param i 
     */
    public void createGrid(int i) {
        for (int[] grid1 : grid) {
            for (int col = 0; col < grid1.length; col++) {
                grid1[col] = 0;
            }
        }
    }

    /**
     * prints out a grid of filled in boxes and empty boxes
     * @return 
     */
    public String toHTML() {
        String guiGrid = "<html>";
        for (int[] grid1 : grid) {
            for (int col = 0; col < grid1.length; col++) {
                if (grid1[col] == 0) {
                    guiGrid += EMPTY + " ";
                } else {
                    guiGrid += FILLED + " ";
                }
            }
            guiGrid += "<br>";
        }
        return guiGrid;
    }

    /**
     * returns a string which consists of a grid of 1s and 0s
     * @return 
     */
    @Override
    public String toString() {
        String output = "";
        for (int[] grid1 : grid) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid1[col] == 1) {
                    output += " 1 ";
                } else {
                    output += " 0 ";
                }
            }
            output += "\n";
        }
        return output;
    }
}
