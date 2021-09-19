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

    public int countAliveDots(int x, int y){
        int count = 0;

        count += getDotState(x - 1, y - 1);
        count += getDotState(x, y - 1);
        count += getDotState(x + 1, y - 1);

        count += getDotState(x - 1, y);
        count += getDotState(x + 1, y);

        count += getDotState(x - 1, y + 1);
        count += getDotState(x, y + 1);
        count += getDotState(x + 1, y + 1);

        return count;
    }

    public int getDotState(int x, int y){
        //check if the corresponding dot is outside the board
        if (x < 0 || x >= gridWidth || y < 0 || y >= gridHeight){
            return 0;
        }else {
            return grid[x][y];
        }
    }

    public static void main(String[] args) {
        Main game = new Main(8, 8);

        game.setAlive(game.grid, 2,2);
        game.setAlive(game.grid,3,2);
        game.setAlive(game.grid,4,2);

        game.printGrid();
        System.out.println("alive neighbour: " + (game.countAliveDots(3, 2)));

    }


}
