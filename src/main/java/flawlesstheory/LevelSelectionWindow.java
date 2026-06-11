package flawlesstheory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelSelectionWindow extends JFrame {

    public static String PROPERTY_NAME = "levelData";
    private static String LOAD_BUTTON_COMMAND = "loadLevel";
    private LevelData levelData;

    public LevelSelectionWindow(String title) {
        super(title);
    }

    public void setup() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0, 0));
        JButton button = new JButton("Выбрать файл уровня");
        button.setActionCommand(LOAD_BUTTON_COMMAND);
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                LevelData data = readLevelData(fileChooser.getSelectedFile());
                if (data != null) {
                    LevelData oldData = levelData;
                    this.levelData = data;
                    this.firePropertyChange(PROPERTY_NAME, oldData, this.levelData);
                }
            }
        });
        this.add(button, BorderLayout.CENTER);
        this.pack();
    }

    private LevelData readLevelData(File level) {
        char[][] tiles = new char[15][20];
        int playerY = -1;
        int playerX = -1;

        try {
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
        } catch (Exception e) {
            JDialog errorDialog = new JDialog();
            errorDialog.setTitle("Катастрофическая ошибка!");
            errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("Что-то пошло не так при загрузке уровня");
            errorDialog.add(label);
            errorDialog.pack();
            errorDialog.setVisible(true);
            errorDialog.setLocationRelativeTo(null);
            return null;
        }

        return new LevelData(tiles, playerX, playerY);
    }

}
