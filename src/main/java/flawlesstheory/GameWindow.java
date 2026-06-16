package flawlesstheory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow extends JFrame {

    public void init() {
        showMenu();
    }

    public void showMenu() {
        MenuPanel panel = new MenuPanel();
        panel.init();
        panel.addPropertyChangeListener(MenuPanel.PROPERTY_NAME, event -> {
            this.remove(panel);
            loadGame((LevelData) event.getNewValue());
        });
        this.add(panel);
        this.pack();
    }

    public void loadGame(LevelData levelData) {
        GameWindow ref = this;

        GameFieldPanel panel = new GameFieldPanel(levelData.getTiles(), levelData.getPlayerPosX(), levelData.getPlayerPosY());
        panel.init();
        panel.addPropertyChangeListener(GameFieldPanel.PROPERTY_NAME, event -> {
            JDialog winDialog = new JDialog(this, "Победа!");
            winDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            winDialog.setLayout(new BorderLayout(0, 0));
            winDialog.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                    /// none
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    /// none
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    e.getWindow().dispose();
                    ref.remove(panel);
                    ref.showMenu();
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    /// none
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    /// none
                }

                @Override
                public void windowActivated(WindowEvent e) {
                    /// none
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                    /// none
                }
            });

            JLabel text = new JLabel("Закройте диалог, чтобы вернуться в меню.");
            text.setPreferredSize(new Dimension(200, 50));
            winDialog.add(text, BorderLayout.CENTER);
            winDialog.pack();
            winDialog.setVisible(true);
            winDialog.setLocationRelativeTo(this);
        });
        this.add(panel);
        this.pack();
    }

}
