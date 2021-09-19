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
                            line.append(" # ");
                        }
                    }
                }
            }
            System.out.println(line);
        }
        System.out.print("\n");
    }

    public void setAlive(int[][] grid, int x, int y){
        grid[x][y] = 1;
    }

    public void setDead(int[][] grid, int x, int y){
        grid[x][y] = 0;
    }

    public int countNeighbour(int x, int y){
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
            for (int x = 0; x < gridWidth; x++) {
                int aliveNeighbours = countNeighbour(x, y);
                if (getCellState(x, y) == 1){
                    if (aliveNeighbours < 2) {
                        setDead(newGrid, x, y);
                    } else if (aliveNeighbours < 4) {
                        setAlive(newGrid, x, y);
                    } else {
                        setDead(newGrid, x, y);
                    }
                }else {
                    if (aliveNeighbours == 3){
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
    int gameMode;
    int gamePattern;
    int generations;
    int gridHeight;
    int gridWidth;
    int numOfCells;
    int[][] grid;

    public void printMenu() {
        System.out.println("*************** GAME OF LIFE ***************");
        System.out.println("               Rifqi Naufal T\n");
        System.out.println("# Game Mode");
        System.out.println("Please choose game mode: ");
        System.out.println("1. Play with pre-defined inputs.");
        System.out.println("2. Set up your own game.");
        System.out.print("Your answer (1 or 2): ");
        gameMode = reader.nextInt();
        switch (gameMode){
            case 1 -> {
                System.out.println("\n# Please choose game pattern: ");
                System.out.println("1. Pulsar (period 3)");
                System.out.println("2. Gosper's Glider Gun");
                System.out.println("3. The R-pentomino");
                System.out.print("Your answer (1, 2, or 3): ");
                gamePattern = reader.nextInt();
                switch (gamePattern) {
                    case 1 -> initPulsarPattern();
                    case 2 -> initGosperGliderGun();
                    case 3 -> initRPentomino();
                    default -> System.out.println("Error: you can only choose 1, 2, or 3!");
                }
            }
            case 2 -> setUpGame();
            default -> System.out.println("Error: you can only choose 1 or 2!");
        }
        System.out.println("\n----------- Game of Life Begins! -----------");
    }

    public void initPulsarPattern(){
        generations = 1000;
        gridWidth = 17;
        gridHeight = 17;
        int [][] grid = new int[gridWidth][gridHeight];
        grid[4][2] = 1;
        grid[5][2] = 1;
        grid[6][2] = 1;
        grid[10][2] = 1;
        grid[11][2] = 1;
        grid[12][2] = 1;

        grid[2][4] = 1;
        grid[2][5] = 1;
        grid[2][6] = 1;
        grid[7][4] = 1;
        grid[7][5] = 1;
        grid[7][6] = 1;
        grid[9][4] = 1;
        grid[9][5] = 1;
        grid[9][6] = 1;
        grid[14][4] = 1;
        grid[14][5] = 1;
        grid[14][6] = 1;

        grid[4][7] = 1;
        grid[5][7] = 1;
        grid[6][7] = 1;
        grid[10][7] = 1;
        grid[11][7] = 1;
        grid[12][7] = 1;

        grid[4][9] = 1;
        grid[5][9] = 1;
        grid[6][9] = 1;
        grid[10][9] = 1;
        grid[11][9] = 1;
        grid[12][9] = 1;

        grid[2][10] = 1;
        grid[2][11] = 1;
        grid[2][12] = 1;
        grid[7][10] = 1;
        grid[7][11] = 1;
        grid[7][12] = 1;
        grid[9][10] = 1;
        grid[9][11] = 1;
        grid[9][12] = 1;
        grid[14][10] = 1;
        grid[14][11] = 1;
        grid[14][12] = 1;

        grid[4][14] = 1;
        grid[5][14] = 1;
        grid[6][14] = 1;
        grid[10][14] = 1;
        grid[11][14] = 1;
        grid[12][14] = 1;

        this.grid = grid;
    }

    public void initGosperGliderGun(){
        generations = 1000;
        gridWidth = 38;
        gridHeight = 24;
        int [][] grid = new int[gridWidth][gridHeight];

        grid[1][5] = 1;
        grid[1][6] = 1;
        grid[2][5] = 1;
        grid[2][6] = 1;

        grid[11][5]= 1;
        grid[11][6]= 1;
        grid[11][7]= 1;

        grid[12][4]= 1;
        grid[12][8]= 1;

        grid[13][3]= 1;
        grid[13][9]= 1;

        grid[14][3]= 1;
        grid[14][9]= 1;

        grid[15][6]= 1;

        grid[16][4]= 1;
        grid[16][8]= 1;

        grid[17][5]= 1;
        grid[17][6]= 1;
        grid[17][7]= 1;

        grid[18][6]= 1;

        grid[21][3]= 1;
        grid[21][4]= 1;
        grid[21][5]= 1;

        grid[22][3]= 1;
        grid[22][4]= 1;
        grid[22][5]= 1;

        grid[23][2]= 1;
        grid[23][6]= 1;

        grid[25][1]= 1;
        grid[25][2]= 1;
        grid[25][6]= 1;
        grid[25][7]= 1;

        grid[24][10] = 1;
        grid[24][12] = 1;

        grid[25][11] = 1;
        grid[25][12] = 1;

        grid[26][11] = 1;

        grid[31][18] = 1;

        grid[32][19] = 1;
        grid[32][20] = 1;

        grid[33][18] = 1;
        grid[33][19] = 1;

        grid[35][3]= 1;
        grid[35][4]= 1;
        grid[36][3]= 1;
        grid[36][4]= 1;

        this.grid = grid;
    }

    public void initRPentomino(){
        generations = 500;
        gridWidth = 45;
        gridHeight = 35;
        int [][] grid = new int[gridWidth][gridHeight];

        grid[22][17] = 1;
        grid[21][17] = 1;
        grid[22][16] = 1;
        grid[23][16] = 1;
        grid[22][18] = 1;

        this.grid = grid;
    }

    public void setUpGame(){
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
