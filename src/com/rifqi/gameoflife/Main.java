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

    public void whenTopOrBottom(int y, StringBuilder line) {
        if (y == 0 || y == gridHeight + 1) {
            line.append(" _");
        } else {
            line.append(" |");
        }
    }

    public void printGrid() {
        for (int y = 0; y < gridHeight + 2; y++) {
            StringBuilder line = new StringBuilder();
            whenTopOrBottom(y, line);
            for (int x = 0; x < gridWidth; x++) {
                if (y == 0 || y == gridHeight + 1) {
                    line.append(" _");
                } else {
                    if (grid[x][y - 1] == 0) {
                        line.append(" .");
                    } else {
                        line.append(" *");
                    }
                }
            }
            whenTopOrBottom(y, line);
            System.out.println(line);
        }
    }

    public void setAlive(int[][] grid, int x, int y){
        grid[x][y] = 1;
    }

    public void setDead(int[][] grid, int x, int y){
        grid[x][y] = 0;
    }

    public static void main(String[] args) {
        Main game = new Main(8, 8);

        game.setAlive(game.grid, 2,2);
        game.setAlive(game.grid,3,2);
        game.setAlive(game.grid,4,2);

        game.printGrid();

    }


}
