package game;

import game.board.Board;
import game.direction.Direction;

public interface Game {
    void init();
    boolean canMove();
    void move(Direction direction);
    void addItem();
    Board getGameBoard();
    boolean hasWin();
}
