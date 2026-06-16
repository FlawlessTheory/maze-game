package flawlesstheory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFieldPanel extends CustomPanel {

    public static final String PROPERTY_NAME = "win";
    private static final int TILE_SIZE = 40;
    private final char[][] tiles;
    private int playerPosX;
    private int playerPosY;

    public GameFieldPanel(char[][] tiles, int playerPosX, int playerPosY) {
        this.tiles = tiles;
        this.playerPosX = playerPosX;
        this.playerPosY = playerPosY;
    }

    public void init() {
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                /// none
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        shiftY(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        shiftY(1);
                        break;
                    case KeyEvent.VK_LEFT:
                        shiftX(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        shiftX(1);
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
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g.clearRect(0, 0, getWidth(), getHeight());
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                if (tiles[y][x] == 'x') {
                    g2d.setColor(Color.BLACK);
                    g2d.fill(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
                } else if (tiles[y][x] == 'P') {
                    g2d.setColor(Color.RED);
                    g2d.drawOval(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                } else if (tiles[y][x] == 'O') {
                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }
            }
        }
    }

    private void shiftY(int shift) {
        if (tiles[playerPosY + shift][playerPosX] == 'O') {
            win();
        } else if (tiles[playerPosY + shift][playerPosX] == ' ') {
            tiles[playerPosY][playerPosX] = ' ';
            tiles[playerPosY + shift][playerPosX] = 'P';
            playerPosY += shift;
            repaint();
        }
    }

    private void shiftX(int shift) {
        if (tiles[playerPosY][playerPosX + shift] == 'O') {
            win();
        } else if (tiles[playerPosY][playerPosX + shift] == ' ') {
            tiles[playerPosY][playerPosX] = ' ';
            tiles[playerPosY][playerPosX + shift] = 'P';
            playerPosX += shift;
            repaint();
        }
    }

    private void win(){
        this.firePropertyChange(PROPERTY_NAME, null, null);
    }
}
