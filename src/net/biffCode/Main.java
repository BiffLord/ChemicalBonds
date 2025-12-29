package net.biffCode;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Chemical Bonds");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setResizable(false);
        GameScreen screen = new GameScreen();
        window.add(screen);
        window.pack();
        window.setIconImage(new ImageIcon(Main.class.getResource("/Bonds.png")).getImage());
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        screen.startGameThread();

    }
}
