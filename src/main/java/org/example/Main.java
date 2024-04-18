package org.example;

public class Main {

    public static void main(String[] args) {
        Board.generateBoard();
        Board.displayBoard();
        Board.firstMove();
        int column = Board.firstMoveCoords[0];
        int row = Board.firstMoveCoords[1];
        Board.hideMines();
        Board.calculateNeighbouringMines();
        Board.revealTiles(column, row);
        Board.displayBoard();

        while(!Board.gameLost) {
            Board.playerMove();
            if (Board.gameLost) {
                Board.revealAll();
                Board.displayBoard();
                System.out.println("Game over! You stepped on a mine.");
                break;
            }
            Board.displayBoard();
            if (Board.totalTiles - Board.revealedCount == Board.totalMines) {
                Board.revealAll();
                Board.displayBoard();
                System.out.println("Congratulations! You won the game.");
                break;
            }
        }


    }
}
