package net.biffCode.entity;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    int speed;
    public BufferedImage spriteSheet;
    public String direction;
    public byte spriteIcon = 0;
    public int spriteVersionCount = 0;
    public Rectangle hitBox;
    boolean collisionOn = false;
}
