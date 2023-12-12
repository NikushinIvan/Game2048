package test;

import game2048.board.Board;
import game2048.game.Game;
import game2048.game.Game2048;
import game2048.board.SquareBoard;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
    }
}
