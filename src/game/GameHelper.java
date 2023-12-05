package game;

import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEquals(List<Integer> list) {
        moveToBegin(list);
        mergeEquals(list);
        moveToBegin(list);
        return list;
    }

    private void moveToBegin(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                for (int j = i+1; j < list.size(); j++) {
                    if (list.get(j) != null) {
                        list.set(i, list.get(j));
                        list.set(j, null);
                        break;
                    }
                }
            }
        }
    }

    private void mergeEquals(List<Integer> list) {
        int i = 0;
        while (i < list.size()) {
            if (i+1 < list.size()) {
                if ((list.get(i) != null) && (list.get(i) == list.get(i+1))) {
                    list.set(i, list.get(i) * 2);
                    list.set(i+1, null);
                    i += 2;
                    continue;
                }
            }
            ++i;
        }
    }
}
