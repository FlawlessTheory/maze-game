package flawlesstheory;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setTitle("Swing Sandbox");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.init();
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

}