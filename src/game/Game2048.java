package game;

import game.board.Board;
import game.direction.Direction;
import game.key.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    GameHelper helper = new GameHelper();
    Board board;
    Random random = new Random();

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {
        int sizeBoard = board.getWidth() * board.getHeight();

        int[] two = generateTwo(sizeBoard);
        List<Integer> list = new ArrayList<>(sizeBoard);
        for (int i = 0; i < sizeBoard; i++) {
            if ((i == two[0]) || (i == two[1])) {
                list.add(2);
            } else {
                list.add(null);
            }
        }
        board.fillBoard(list);
    }

    @Override
    public boolean canMove() {
        return board.hasValue(null) || canMoveToDirection(Direction.RIGHT) || canMoveToDirection(Direction.UP);
    }

    @Override
    public void move(Direction direction) {
        boolean directionIsRightOrLeft = (direction == Direction.RIGHT) || (direction == Direction.LEFT);
        int size;
        if (directionIsRightOrLeft) {
            size = board.getHeight();
        } else {
            size = board.getWidth();
        }

        for (int i = 0; i < size; i++) {
            List<Key> keys;
            keys = directionIsRightOrLeft ? board.getRow(i) : board.getColumn(i);
            if (direction == Direction.LEFT || direction == Direction.UP) {
                Collections.reverse(keys);
            }
            List<Integer> values = moveToKeysValue(keys);
            for (int index = 0; index < keys.size(); index++) {
                board.addItem(keys.get(index), values.get(index));
            }
        }
    }

    @Override
    public void addItem() {
        int newItem;
        if ((random.nextInt(10) + 1) == 1) {
            newItem = 4;
        } {
            newItem = 2;
        }
        List<Key> availableKeys = board.availableSpace();
        int randomIndex = random.nextInt(availableKeys.size());
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

    private int[] generateTwo(int size) {
        int two1 = random.nextInt(size);
        int two2 = random.nextInt(size);
        if (two1 == two2) {
            if (two1 != 0) {
                two1 = 0;
            } else {
                two1 = 1;
            }
        }
        return new int[] {two1, two2};
    }

    private boolean canMoveToDirection(Direction direction) {
        for (int i = 0; i < board.getHeight(); i++) {
            List<Key> keys;
            if (direction == Direction.RIGHT || direction == Direction.LEFT) {
                keys = board.getRow(i);
            } else {
                keys = board.getColumn(i);
            }
            List<Integer> values = new ArrayList<>();
            for (Key key : keys) {
                values.add(board.getValue(key));
            }
            if (!values.equals(helper.moveAndMergeEquals(values))) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> moveToKeysValue(List<Key> keys) {
        List<Integer> values = new ArrayList<>();
        for (Key key : keys) {
            values.add(board.getValue(key));
        }
        return helper.moveAndMergeEquals(values);
    }
}
