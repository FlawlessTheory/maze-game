package flawlesstheory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame {

    private final LevelData levelData;

    public GameWindow(String title, LevelData levelData) {
        super(title);
        this.levelData = levelData;
    }

    public void setup() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0, 0));

        GameField field = new GameField(levelData.getTiles(), levelData.getPlayerPosX(), levelData.getPlayerPosY());
        field.setFocusable(true);
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                /// none
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        field.shiftY(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        field.shiftY(1);
                        break;
                    case KeyEvent.VK_LEFT:
                        field.shiftX(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        field.shiftX(1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                /// none
            }
        });
        this.add(field, BorderLayout.CENTER);
        this.pack();
    }

}
