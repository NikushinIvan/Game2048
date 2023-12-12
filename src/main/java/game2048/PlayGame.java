package game2048;

import game2048.userInterface.Game2048Panel;

import javax.swing.*;

public class PlayGame {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(352, 372);
        game.setResizable(false);

        game.add(new Game2048Panel());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
