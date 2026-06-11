package flawlesstheory;

public class LevelData {

    private final char[][] tiles;
    private final int playerPosX;
    private final int playerPosY;

    public LevelData(char[][] tiles, int playerPosX, int playerPosY) {
        this.tiles = tiles;
        this.playerPosX = playerPosX;
        this.playerPosY = playerPosY;
    }

    public char[][] getTiles() {
        return tiles;
    }

    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }

}
