package flawlesstheory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static int width = 815;
    private static int height = 640;

    public static void main(String[] args) throws FileNotFoundException {
        char[][] tiles = new char[15][20];
        int playerY = -1;
        int playerX = -1;

        File level = new File("S:\\swing-sandbox\\src\\main\\resources\\level.txt");
        Scanner sc = new Scanner(level);
        int y = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("P")) {
                playerY = y;
                playerX = line.indexOf('P');
            }
            tiles[y] = line.toCharArray();
            y++;
        }
        sc.close();

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        JFrame frame = new JFrame("Swing Sandbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        GameField field = new GameField(tiles, playerX, playerY);
        field.setFocusable(true);
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                /// none
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("клац");
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: field.shiftY(-1); break;
                    case KeyEvent.VK_DOWN: field.shiftY(1); break;
                    case KeyEvent.VK_LEFT: field.shiftX(-1); break;
                    case KeyEvent.VK_RIGHT: field.shiftX(1); break;
                    default: break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                /// none
            }
        });
        frame.add(field);
        frame.setVisible(true);
    }
}