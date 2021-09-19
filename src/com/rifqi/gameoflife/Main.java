package com.rifqi.gameoflife;

public class Main {
    int gridWidth;
    int gridHeight;
    int[][] grid;

    public Main(int gridWidth, int gridHeight) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        this.grid = new int[gridWidth][gridHeight];
    }

    public void printGrid() {
        for (int y = 0; y < gridHeight + 2; y++) {
            StringBuilder line = new StringBuilder();
            //checking if at top or bottom of line
            if (y == 0 || y == gridHeight + 1){
                for (int x = 0; x < gridWidth + 2; x++) {
                    line.append(" = ");
                }
            }else {
                for (int x = 0; x < gridWidth + 2; x++) {
                    //checking if at beginning or end of line
                    if (x == 0 || x == gridWidth + 1){
                        line.append(" | ");
                    }
                    //if not at the edge of grid, print the content of array grid
                    else {
                        if (grid[x - 1][y - 1] == 0){
                            line.append(" . ");
                        }else {
                            line.append(" * ");
                        }
                    }
                }
            }
            System.out.println(line);
        }
        System.out.println("");
    }

    public void setAlive(int[][] grid, int x, int y){
        grid[x][y] = 1;
    }

    public void setDead(int[][] grid, int x, int y){
        grid[x][y] = 0;
    }

    public int countAliveCells(int x, int y){
        int count = 0;

        count += getCellState(x - 1, y - 1);
        count += getCellState(x, y - 1);
        count += getCellState(x + 1, y - 1);

        count += getCellState(x - 1, y);
        count += getCellState(x + 1, y);

        count += getCellState(x - 1, y + 1);
        count += getCellState(x, y + 1);
        count += getCellState(x + 1, y + 1);

        return count;
    }

    public int getCellState(int x, int y){
        //check if the corresponding cell is outside the board
        if (x < 0 || x >= gridWidth || y < 0 || y >= gridHeight){
            return 0;
        }else {
            return grid[x][y];
        }
    }

    public void next(){
        int[][] newGrid = new int[gridWidth][gridHeight];

        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridHeight; x++) {
                int aliveCells = countAliveCells(x, y);
                if (getCellState(x, y) == 1){
                    if (aliveCells < 2){
                        setDead(newGrid, x, y);
                    }else if (aliveCells < 4){
                        setAlive(newGrid, x, y);
                    }else{
                        setDead(newGrid, x, y);
                    }
                }else {
                    if (aliveCells == 3){
                        setAlive(newGrid, x, y);
                    }
                }
            }
        }
        grid = newGrid;
    }



    public static void main(String[] args) {
        Main game = new Main(8, 8);

        game.setAlive(game.grid, 2,2);
        game.setAlive(game.grid,3,2);
        game.setAlive(game.grid,4,2);

        game.printGrid();
        game.next();
        game.printGrid();
        game.next();
        game.printGrid();
        game.next();

    }


}
