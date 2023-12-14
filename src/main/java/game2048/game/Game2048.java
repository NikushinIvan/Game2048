package game2048.game;

import game2048.board.Board;
import game2048.board.SquareBoard;
import game2048.direction.Direction;
import game2048.exception.NotEnoughSpase;
import game2048.key.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final GameHelper helper = new GameHelper();
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    private final Random random = new Random();

    @Override
    public void init() {
        var sizeBoard = board.getWidth() * board.getHeight();

        List<Integer> list = new ArrayList<>(sizeBoard);
        for (int i = 0; i < sizeBoard; i++) {
            list.add(null);
        }
        board.fillBoard(list);

        try {
            addItem();
            addItem();
        } catch (NotEnoughSpase e) {
            System.err.println("Can't add item");
        }
    }

    @Override
    public boolean canMove() {
        return board.hasValue(null) || canMoveToDirection(Direction.LEFT) || canMoveToDirection(Direction.UP);
    }

    @Override
    public boolean move(Direction direction) {
        if (canMove()) {
            var isMovedField = false;
            var isHorizontalDirection = (direction == Direction.RIGHT) || (direction == Direction.LEFT);
            var sizeKeys = isHorizontalDirection ? board.getHeight() : board.getWidth();

            for (int i = 0; i < sizeKeys; i++) {
                List<Key> keys = isHorizontalDirection ? board.getRow(i) : board.getColumn(i);
                if (direction == Direction.RIGHT || direction == Direction.DOWN) {
                    Collections.reverse(keys);
                }
                if (moveToKeysValue(keys)) isMovedField = true;
            }
            if (isMovedField) {
                try {
                    addItem();
                } catch (NotEnoughSpase e) {
                    System.err.println("Can't add item");
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() throws NotEnoughSpase {
        if (!board.hasValue(null)) {
            throw new NotEnoughSpase();
        }
        int newItem;
        if ((random.nextInt(10)) == 1) {
            newItem = 4;
        } else {
            newItem = 2;
        }
        List<Key> availableKeys = board.availableSpace();
        var randomIndex = random.nextInt(availableKeys.size());
        board.addItem(availableKeys.get(randomIndex), newItem);
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private boolean canMoveToDirection(Direction direction) {
        for (int i = 0; i < board.getHeight(); i++) {
            List<Key> keys;
            if (direction == Direction.RIGHT || direction == Direction.LEFT) {
                keys = board.getRow(i);
            } else {
                keys = board.getColumn(i);
            }
            var values = board.getValues(keys);
            var newValues = helper.moveAndMergeEquals(values);
            for (int index = 0; index < values.size(); index++) {
                if (!values.get(index).equals(newValues.get(index))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean moveToKeysValue(List<Key> keys) {
        boolean isMovedRow = false;

        var values = board.getValues(keys);

        values = helper.moveAndMergeEquals(values);

        for (int index = 0; index < keys.size(); index++) {
            if (board.getValue(keys.get(index)) != (values.get(index))) {
                isMovedRow = true;
            }
            board.addItem(keys.get(index), values.get(index));
        }
        return isMovedRow;
    }
}
