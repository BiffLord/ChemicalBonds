package net.biffCode.tiles;

import net.biffCode.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GameScreen screen;
    Tile[] tiles;
    int[][] mapTiles;

    public TileManager(GameScreen screen){
        this.screen = screen;
        tiles = new Tile[10];
        mapTiles = new int[screen.totalColumns][screen.totalRows];
        getTileImage();
        loadMap("/maps/map1.txt");
    }
    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Brick00.png")));
            tiles[0].collision = true;
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Grass00.png")));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Grass01.png")));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Brick01.png")));
        } catch(IOException IOE){
            IOE.printStackTrace();
        }
    }
    public void loadMap(String map){
        try{
            InputStream inputStream = getClass().getResourceAsStream(map);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while (col < screen.totalColumns && row < screen.totalRows){
                String line = reader.readLine();
                while(col < screen.totalColumns){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if (col == screen.totalColumns){
                    col = 0;
                    row++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < screen.totalColumns && worldRow < screen.totalRows){
            int worldX = worldColumn*screen.tileSize;
            int worldY = worldRow*screen.tileSize;
            int x = worldX - screen.player.worldX +screen.player.screenX;
            int y = worldY - screen.player.worldY+screen.player.screenY;
            if (worldX > screen.player.worldX - screen.player.screenX -64 &&
                    worldX < screen.player.worldX + screen.player.screenX+64 &&
                    worldY > screen.player.worldY - screen.player.screenY -64 &&
                    worldY < screen.player.worldY + screen.player.screenY+64){
                g2d.drawImage(tiles[mapTiles[worldColumn][worldRow]].image, x, y, screen.tileSize, screen.tileSize, null);
            }
            worldColumn++;
            if (worldColumn == screen.totalColumns) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }

}

