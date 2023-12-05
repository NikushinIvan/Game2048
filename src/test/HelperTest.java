package test;

import game.GameHelper;

import java.util.Arrays;

public class HelperTest {
    private final static GameHelper helper = new GameHelper();

    public static void main(String[] args) {
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(1, 2, null, 3)), Arrays.asList(1, 2, 3, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(2, 2, null, 3)), Arrays.asList(4, 3, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(2, 2, 4, 4)), Arrays.asList(4, 8, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(2, 2, 2, 3)), Arrays.asList(4, 2, 3, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(2, null, null, 2)), Arrays.asList(4, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(null, null, null, null)), Arrays.asList(null, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(null, null, null, 2)), Arrays.asList(2, null, null, null));
        BoardTest.assertEquals(helper.moveAndMergeEquals(Arrays.asList(null, null, 2, 2)), Arrays.asList(4, null, null, null));

    }
}
