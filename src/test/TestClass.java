package test;

import game.board.Board;
import game.Game;
import game.Game2048;
import game.board.SquareBoard;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048(board);
        System.out.println(game2048.canMove());
    }
}
