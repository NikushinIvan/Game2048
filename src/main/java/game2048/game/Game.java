package game2048.game;

import game2048.board.Board;
import game2048.direction.Direction;
import game2048.exception.NotEnoughSpase;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpase;
    Board getGameBoard();
    boolean hasWin();
}
