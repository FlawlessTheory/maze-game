package flawlesstheory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class MenuPanel extends CustomPanel {

    public static String PROPERTY_NAME = "levelData";
    private static String LOAD_BUTTON_COMMAND = "loadLevel";
    private LevelData levelData = null;

    public void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton button = new JButton("Выбрать файл уровня");
        button.setActionCommand(LOAD_BUTTON_COMMAND);
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new LevelFileFilter());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                LevelData data = readLevelData(fileChooser.getSelectedFile());
                if (data != null) {
                    LevelData oldData = levelData;
                    this.levelData = data;
                    this.firePropertyChange(PROPERTY_NAME, oldData, this.levelData);
                }
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button);
        this.add(Box.createVerticalGlue());

        JLabel headerLabel = new JLabel("СПРАВКА О ФОРМАТЕ ФАЙЛА УРОВНЯ");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(headerLabel);
        this.add(Box.createVerticalGlue());

        String text = String.join("\n",
                "Уровень составляется из следующих символов латинского алфавита:",
                "- x - непроходимая клетка;",
                "- P - начальное положение игрока;",
                "- O - точка, до которой нужно добраться;",
                "- пробел - проходимая клетка.",
                "Лабиринт должен быть размером ровно 20х15 клеток. Выход игрока за пределы границ приведёт к ошибке, поэтому окружайте лабиринт стенами!",
                "Расширение файла лабиринта - .lvl");
        JTextArea helpText = new JTextArea(text);
        helpText.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpText.setLineWrap(true);
        helpText.setEditable(false);
        this.add(helpText);
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
