package game.board;

import game.key.Key;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SquareBoard extends Board {
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<Integer> list) {
        board.clear();
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < super.getWidth(); i++) {
            for (int j = 0; j < super.getHeight(); j++) {
                Key key = new Key(i, j);
                if (iter.hasNext()) {
                    board.put(key, iter.next());
                }
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> availableKey = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (board.get(key) == null) {
                availableKey.add(key);
            }
        }
        return availableKey;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if ((key.getI() == i) && (key.getJ() == j)) {
                return  key;
            }
        }
        return null;
    }

    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> keyColumn = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (key.getJ() == j) {
                keyColumn.add(key);
            }
        }
        return keyColumn;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> keyRow = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (key.getI() == i) {
                keyRow.add(key);
            }
        }
        return keyRow;
    }

    @Override
    public boolean hasValue(Integer value) {
        Collection<Integer> values = board.values();
        return values.contains(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        List<Integer> values = new ArrayList<>();
        for (Key key : keys) {
            if (board.containsKey(key)) {
                values.add(board.get(key));
            }
        }
        return values;
    }
}
