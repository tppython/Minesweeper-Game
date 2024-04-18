package org.example;

public class Board {
    // Create our Minesweeper board

    static int rows = 9;
    static int columns = 9;
    static int[][] board = new int[rows][columns];
    static int[][] gameBoard = new int[rows][columns];
    static int[] firstMoveCoords = new int[2];
    static int totalTiles = rows * columns;
    static int revealedCount = 0;
    static boolean[][] revealedTiles = new boolean[rows][columns];
    static int totalMines = 9;
    static boolean gameLost = false;


    public static void generateBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = 50;
                gameBoard[i][j] = 0;
                revealedTiles[i][j] = false;
            }
        }
    }

    public static void hideMines() {
        int minesHidden = 0;
        while (minesHidden < totalMines) {
            int randRow = (int) (Math.random() * rows);
            int randColumn = (int) (Math.random() * columns);
            if (board[randColumn][randRow] != 100 && board[randRow][randColumn] != 0) {
                board[randColumn][randRow] = 100;
                gameBoard[randColumn][randRow] = 100;
                minesHidden++;
            }
        }
    }

    public static boolean playableTile(int r, int c) {
        return (r >= 0 && r < rows && c >= 0 && c < columns);
    }

    public static void calculateNeighbouringMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                int count = 0;
                if (board[i][j] != 100) {

                    for (int x=-1; x<=1; x++) {
                        for (int y=-1; y<=1; y++) {
                            if ((x != 0 || y != 0) && playableTile(i + x, j + y) && board[i + x][j + y] == 100) {
                                count++;
                            }
                        }
                    }

                    gameBoard[i][j] = count;
                }
            }
        }
    }

    public static void displayBoard() {

        System.out.print("\t");
        for (int i=0; i<rows; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println("\n");
        for (int i=0; i<rows; i++) {
            System.out.print(i + "\t");

            for (int j=0; j<columns; j++) {

                if (board[i][j] == 'F') {
                    System.out.print(" F ");
                } else if (revealedTiles[i][j]) {
                    if (gameBoard[i][j] == 0) {
                        System.out.print(" 0 ");
                    } else if (gameBoard[i][j] == 100) {
                        System.out.print(" M ");
                    } else {
                        System.out.print(" " + gameBoard[i][j] +" ");
                    }
                } else {
                    System.out.print(" - ");
                }
                System.out.print(" ");
                }

            System.out.println();
        }
    }

    public static void firstMove() {
        int c = UserHandling.revealMove("Enter the column for your first move: ");
        int r = UserHandling.revealMove("Enter the row for your first move: ");
        board[r][c] = 0;
        gameBoard[r][c] = 0;
        firstMoveCoords[0] = r;
        firstMoveCoords[1] = c;
    }

    public static void playerMove() {
        char x = UserHandling.moveType("For your next move you can either reveal a tile or flag a tile. Press 'r' to reveal or 'f' to flag");
        int c = UserHandling.revealMove("Enter the column for your next move: ");
        int r = UserHandling.revealMove("Enter the row for your next move: ");

        if (x == 'f') {
            board[r][c] = 'F';
        } else {
            revealTiles(r, c);
        }
    }

    public static void revealTiles(int col, int row) {
        if (!playableTile(col, row) || revealedTiles[col][row]) {
            return;
        }
        revealedTiles[col][row] = true;
        revealedCount++;
        if (gameBoard[col][row] == 0 || board[col][row] == 0) {
            for (int x=-1; x<=1; x++) {
                for (int y=-1; y<=1; y++) {
                    revealTiles(col + x, row + y);
                }
            }
        } else if (gameBoard[col][row] == 100) {
            gameLost = true;
        }
    }

    public static void revealAll() {
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                revealedTiles[i][j] = true;
            }
        }
    }

}