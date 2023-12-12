package test;

import game2048.board.Board;
import game2048.board.SquareBoard;
import game2048.key.Key;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    private final static Board board = new SquareBoard<Key>(2);

    public static void main(String[] args) {
        board.fillBoard(Arrays.asList(1, 2, 3, null));
        if (!board.availableSpace().get(0).equals(board.getKey(1,1))) {
            throw new RuntimeException("availableSpace not work");
        }
        if (!board.getKey(0, 0).equals(board.getKey(0, 0))) {
            throw new RuntimeException("Keys must be same");
        }
        if (board.getKey(2, 2) != null) {
            throw new RuntimeException("Not found key must be null");
        }
        if (board.getValue(board.getKey(1, 1)) != null) {
            throw new RuntimeException("getValue not work");
        }
        if (!board.hasValue(null) || !board.hasValue(3)) {
            throw new RuntimeException("hasValue not work");
        }
        assertEquals(board.getColumn(0), Arrays.asList(board.getKey(0, 0), board.getKey(1, 0)));
        assertEquals(board.getRow(0), Arrays.asList(board.getKey(0, 0), board.getKey(0, 1)));
        assertEquals(board.getValues(board.getColumn(0)), Arrays.asList(1, 3));
        board.fillBoard(Arrays.asList(5, 6, 7, 8));
        if (!board.availableSpace().isEmpty()) {
            throw new RuntimeException("fillBoard not correct work");
        }
    }

    public static void assertEquals(List<?> list1, List<?> list2) {
        if (!(list1.size() == list2.size() && list1.containsAll(list2) && list2.containsAll(list1))) {
            throw new RuntimeException("List1:"+list1+" not equals List2: "+list2);
        }
    }
}