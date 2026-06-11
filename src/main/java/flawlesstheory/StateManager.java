package flawlesstheory;

public class StateManager {

    private static String windowTitle = "Swing Sandbox";

    public void init() {
        showLevelSelection();
    }

    private void showLevelSelection() {
        LevelSelectionWindow window = new LevelSelectionWindow(windowTitle);
        window.setup();
        window.addPropertyChangeListener(LevelSelectionWindow.PROPERTY_NAME, event -> {
            loadGame((LevelData) event.getNewValue());
            window.dispose();
        });
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    private void loadGame(LevelData levelData) {
        GameWindow window = new GameWindow(windowTitle, levelData);
        window.setup();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

}
