package net.biffCode.entity;

import net.biffCode.GameScreen;
import net.biffCode.tiles.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.Math;


public class Player extends Entity{
    GameScreen screen;
    public PlayerMovementListner keyListner;
    public int screenX = 480;
    public int screenY = 352;

    public Player(GameScreen GS){
        screen = GS;
        this.setDefault();
        this.loadSpriteSheet();
        direction= "face fore";
        this.setKeyListner();
        hitBox = new Rectangle(16,0,32,32);
    }
    public void setDefault(){
        worldX = screen.tileSize*0;
        worldY = screen.tileSize*0;
        speed = 4;
    }

    public void setKeyListner() {
        this.keyListner = new PlayerMovementListner(this);
    }

    public void update(){
        if (this.keyListner.up ){
            worldY -=4;
            direction = "back";
        }
        else if (this.keyListner.down){
            worldY +=4;
            direction = "forward";
        }
        else if (this.keyListner.west){
            worldX -=4;
            direction = "left";
        }
        else if (this.keyListner.east){
            worldX +=4;
            direction = "right";
        }
        collisionOn = false;
        screen.colide.checkTile(this);
    }
    public void draw(Graphics2D g2d){
        int x = Math.abs(spriteIcon * 32);
        switch (direction){
            case "forward": g2d.drawImage(spriteSheet.getSubimage(x,0,screen.OGtileSize,screen.OGtileSize), 480, 352,64,64, null); break;
            case "back": g2d.drawImage(spriteSheet.getSubimage(x,32,screen.OGtileSize,screen.OGtileSize), 480, 352,64,64, null);break;
            case "left": g2d.drawImage(spriteSheet.getSubimage(x,64,screen.OGtileSize,screen.OGtileSize), 480, 352,64,64, null);break;
            case "right": g2d.drawImage(spriteSheet.getSubimage(x,96,screen.OGtileSize,screen.OGtileSize), 480, 352,64,64, null);break;
            case "face fore": g2d.drawImage(spriteSheet.getSubimage(32,0,screen.OGtileSize, screen.OGtileSize), 480, 352, 64, 64, null);break;
            case "face back": g2d.drawImage(spriteSheet.getSubimage(32,32,screen.OGtileSize, screen.OGtileSize), 480, 352, 64, 64, null);break;
            case "face west": g2d.drawImage(spriteSheet.getSubimage(32,64,screen.OGtileSize, screen.OGtileSize), 480, 352, 64, 64, null);break;
            case "face east": g2d.drawImage(spriteSheet.getSubimage(32,96,screen.OGtileSize, screen.OGtileSize), 480, 352, 64, 64, null);break;
        }
        if (spriteVersionCount == 10){
            spriteVersionCount = 0;
            spriteIcon = (spriteIcon != 2)? (byte) (spriteIcon + 1) :-1;
        } else{
            spriteVersionCount++;
        }
       }
    public void loadSpriteSheet(){
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/entity/player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class PlayerMovementListner implements KeyListener {
        public boolean up, down, east, west;
        Player player;
        public PlayerMovementListner(Player player){
            super();
            this.player = player;
        }
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){

                case KeyEvent.VK_W, KeyEvent.VK_UP:
                    up = true;
                    break;

                case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                    west = true;
                    break;

                case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    down = true;
                    break;

                case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                    east = true;
                    break;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP: up = false; player.direction = "face back"; break;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT: west = false; player.direction = "face west"; break;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN: down = false;player.direction = "face fore"; break;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT: east = false; player.direction = "face east"; break;
            }
        }
    }
}
