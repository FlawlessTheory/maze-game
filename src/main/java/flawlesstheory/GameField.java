package flawlesstheory;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {

    private final static int TILE_SIZE = 40;
    private final char[][] tiles;
    private int playerPosX;
    private int playerPosY;

    public GameField(char[][] tiles, int playerPosX, int playerPosY) {
        this.tiles = tiles;
        this.playerPosX = playerPosX;
        this.playerPosY = playerPosY;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("перерисовка");
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

    public void shiftY(int shift) {
        if (tiles[playerPosY + shift][playerPosX] == 'O') {
            win();
        } else if (tiles[playerPosY + shift][playerPosX] == ' ') {
            tiles[playerPosY][playerPosX] = ' ';
            tiles[playerPosY + shift][playerPosX] = 'P';
            playerPosY += shift;
            repaint();
        }
    }

    public void shiftX(int shift) {
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
        JDialog dialog = new JDialog();
        dialog.setTitle("Победа!");
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(new JLabel("Победа!"));
        dialog.setSize(100, 75);
        dialog.setVisible(true);
    }
}
