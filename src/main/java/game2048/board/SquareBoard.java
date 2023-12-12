package game2048.board;

import game2048.key.Key;

import java.util.*;

public class SquareBoard<V> extends Board<Key, V> {
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<V> list) {
        if (list.size() > getHeight() * getWidth()) {
            throw new RuntimeException("Initialization array is larger than field size");
        }
        board.clear();
        Iterator<V> iter = list.iterator();
        for (int i = 0; i < super.getWidth(); i++) {
            for (int j = 0; j < super.getHeight(); j++) {
                if (iter.hasNext()) {
                    board.put(new Key(i, j), iter.next());
                } else {
                    board.put(new Key(i, j), null);
                }
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> availableKeys = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (board.get(key) == null) {
                availableKeys.add(key);
            }
        }
        return availableKeys;
    }

    @Override
    public void addItem(Key key, V value) {
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
    public V getValue(Key key) {
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
        keyColumn.sort(Comparator.comparingInt(Key::getI));
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
        keyRow.sort(Comparator.comparingInt(Key::getJ));
        return keyRow;
    }

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> values = new ArrayList<>();
        for (Key key : keys) {
            if (board.containsKey(key)) {
                values.add(board.get(key));
            }
        }
        return values;
    }
}
