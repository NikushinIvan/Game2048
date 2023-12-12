package test;

import game2048.exception.NotEnoughSpase;
import game2048.game.Game;
import game2048.game.Game2048;
import game2048.board.Board;
import game2048.board.SquareBoard;
import game2048.direction.Direction;
import game2048.key.Key;

import java.util.Arrays;
import java.util.List;

public class Game2048Test {
    private static final Game game = new Game2048();

    public static void main(String[] args) {
        Board<Key, String> b2 = new SquareBoard<>(1);
        b2.fillBoard(Arrays.asList("hello"));
        if (!"hello".equals(b2.getValue(b2.getKey(0, 0)))) throw  new RuntimeException("getValue not work");
        if (!b2.hasValue("hello")) throw new RuntimeException("hasValue nor work");
        Board<String, Double> b3 = new Board<String, Double>(1, 1) {
            @Override
            public void fillBoard(List<Double> list) {}
            @Override
            public List<String> availableSpace() {return null;}
            @Override
            public void addItem(String key, Double value) {}
            @Override
            public String getKey(int i, int j) {return null;}
            @Override
            public Double getValue(String key) {return null;}
            @Override
            public List<String> getColumn(int j) {return null;}
            @Override
            public List<String> getRow(int i) {return null;}
            @Override
            public boolean hasValue(Double value) {return false;}

            @Override
            public List<Double> getValues(List<String> keys) {return null;}
        };
        Board<Key, Integer> b = game.getGameBoard();
        if (!b.availableSpace().isEmpty()) throw new RuntimeException("Game board must be empty before initialize");
        b.fillBoard(Arrays.asList(2,null,null,8,
                                  2,2,8,8,
                                  2,null,2,2,
                                  4,2,4,2048));
        if (!game.hasWin()) throw new RuntimeException("hasWin not work");
        game.move(Direction.LEFT);
        if (b.availableSpace().size() != 5) throw new RuntimeException("move must be add item");
        assertLists(b.getValues(b.getRow(0)).subList(0,2), Arrays.asList(2,8));
        assertLists(b.getValues(b.getRow(1)).subList(0,2), Arrays.asList(4,16));
        assertLists(b.getValues(b.getRow(3)).subList(0,2), Arrays.asList(4,2));
        game.move(Direction.DOWN);
        assertLists(b.getValues(b.getColumn(0)).subList(1,4), Arrays.asList(2,4, 8));
        assertLists(b.getValues(b.getColumn(1)).subList(1,4), Arrays.asList(8,16, 4));

        game.init();
        if (b.availableSpace().size() != 14) throw new RuntimeException("init must be add 2 item");
        if (!game.canMove()) throw new RuntimeException("canMove not work");
        try {
            game.addItem();
        } catch (NotEnoughSpase e) {
            throw new RuntimeException(e);
        }
        if (b.availableSpace().size() != 13) throw  new RuntimeException("addItem must be add 1 item");
    }

    private static void assertLists(List<?> l1, List<?> l2) {
        if (!(l1.size() == l2.size() && l1.containsAll(l2) && l2.containsAll(l1)))
            throw new RuntimeException("l1: "+l1+" not equals l2: "+l2);
    }
}
