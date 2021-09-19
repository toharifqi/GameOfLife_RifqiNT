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
                if (y == 0 || y == gridHeight + 2) {
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

    }


}
