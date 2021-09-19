package com.rifqi.gameoflife;

import java.util.Scanner;

class Game{
    int gridWidth;
    int gridHeight;
    /*why i use int 2 dimensional array instead of boolean 2D array?
    -> because I want to add the element of living cells to the counting directly (I don't need to check if the cell
    is alive or not, note: alive cell = 1; dead cell = 0)*/
    int[][] grid;

    public Game(int[][] grid) {
        this.gridWidth = grid.length;
        this.gridHeight = grid[0].length;
        this.grid = grid;
    }

    public void drawGrid() {
        for (int y = 0; y < gridHeight + 2; y++) {
            StringBuilder line = new StringBuilder();
            //checking if at top or bottom of line
            if (y == 0 || y == gridHeight + 1){
                line.append(" = ".repeat(Math.max(0, gridWidth + 2)));
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

    public void drawAndNext(){
        drawGrid();
        next();
    }
}

class Menu{
    Scanner reader = new Scanner(System.in);
    int generations;
    int gridHeight;
    int gridWidth;
    int numOfCells;
    int[][] grid;

    public void printMenu() {
        System.out.println("*************** GAME OF LIFE ***************");
        System.out.println("               Rifqi Naufal T\n");
        System.out.println("\n# Game Settings");
        System.out.print("Please input the number of generations (integer): ");
        generations = reader.nextInt();
        System.out.print("Please input grid width (integer): ");
        gridWidth = reader.nextInt();
        System.out.print("Please input grid height (integer): ");
        gridHeight = reader.nextInt();
        int [][] grid = new int[gridWidth][gridHeight];
        System.out.println("\n# Cells Initialization");
        System.out.print("Please input number of initial cells (integer): ");
        numOfCells = reader.nextInt();
        while (numOfCells <= 0 || numOfCells > gridWidth * gridHeight){
            System.out.println("Number of initial cells cannot be 0, negative, or more than " + gridWidth * gridHeight);
            System.out.print("Please input again: ");
            numOfCells = reader.nextInt();
        }
        for (int i = 1; i <= numOfCells; i++) {
            System.out.println("Insert coordinate for cell " + i + " to be alive.");
            System.out.print("X: ");
            int x = reader.nextInt();
            while (x < 0 || x >= gridWidth){
                System.out.print("X must be in range of 0 to " + (gridWidth-1) + ", input X again: ");
                x = reader.nextInt();
            }
            System.out.print("Y: ");
            int y = reader.nextInt();
            while (y < 0 || y >= gridHeight){
                System.out.print("Y must be in range of 0 to " + (gridHeight-1) + ", input Y again: ");
                y = reader.nextInt();
            }
            grid[x][y] = 1;
        }
        this.grid = grid;
        System.out.println("\n----------- Game of Life Begins! -----------");
    }

    public void setUpGame(){

    }
}

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.printMenu();
        Game game = new Game(menu.grid);
        for (int i = 1; i <= menu.generations; i++) {
            System.out.println("Generation #" + i);
            game.drawAndNext();
        }

    }
}
