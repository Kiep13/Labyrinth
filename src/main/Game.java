package main;

import generation.Cell;
import generation.Generator;
import generation.TypeCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game
{

    private static int size;

    private JFrame frame;
    private LPanel panel;

    private Generator generator;
    private Cell[][] labyrinth;
    private Player player;

    private int width;
    private int height;

    private int xLocation;
    private int yLocation;

    private int level;

    private GridBagLayout layout;
    private GridBagConstraints c;


    public Game() {

        player = new Player();
        level = 1;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        xLocation = dim.width/2 - (width/2);
        yLocation = dim.height/2 - (height/2);

        width = size * 10 + 36;
        height  = size * 10 + 59;

        frame = new JFrame();
        frame.setLocation(xLocation, yLocation);
        frame.setSize(width, height);

        ImageIcon icon = new ImageIcon("labyrinth.png");
        frame.setIconImage(icon.getImage());

        layout = new GridBagLayout();
        frame.setLayout(layout);

        c =  new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        generator = new Generator();

        newLevel();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                makePlayerMove(code);
            }
        });

        frame.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent e) {

                width = frame.getWidth();
                height = frame.getHeight();

            }

            public void componentMoved(ComponentEvent e) {

                xLocation = frame.getX();
                yLocation = frame.getY();

            }

        });

    }

    public static void main(String [] args) {

        size = 11;
        new Game();

    }


    public void newLevel() {

        player = new Player();
        labyrinth = generator.generateSimpleTable(size);

        panel = new LPanel(labyrinth, size);
        layout.setConstraints(panel, c);
        frame.getContentPane().add(panel);

        frame.revalidate();
        frame.repaint();
        frame.setTitle(level  + " уровень");

    }

    public void makePlayerMove(int code) {

        boolean isMoved = false;
        int xOffset = 0, yOffset = 0;
        TypeCell symbolOver, symbolUnder,symbolLeft, symbolRight;

        if(this.player.yPosition == 0) {

            symbolOver = TypeCell.WALL;

        } else {

            symbolOver = labyrinth[player.yPosition - 1][player.xPosition].typeCell;

        }

        if(this.player.yPosition == size - 1) {

            symbolUnder = TypeCell.WALL;

        } else {

            symbolUnder = labyrinth[player.yPosition + 1][player.xPosition].typeCell;

        }

        symbolLeft =  labyrinth[player.yPosition][player.xPosition - 1].typeCell;
        symbolRight =  labyrinth[player.yPosition][player.xPosition + 1].typeCell;

        switch (code) {
        case 87 :
        case 38: {
            isMoved = this.player.goUp(symbolOver);
            yOffset = -1;
            }break;
        case 65:
        case 37: {
            isMoved = this.player.goLeft(symbolLeft);
            xOffset = -1;
            }break;
        case 83:
        case 40: {
            isMoved = this.player.goDown(symbolUnder);
            yOffset = 1;
            }break;
        case 68:
        case 39: {
            isMoved = this.player.goRight(symbolRight);
            xOffset = 1;
            }break;
        }

        if(isMoved) {

            labyrinth[player.yPosition][player.xPosition].typeCell = TypeCell.VISITED;

            this.player.xPosition += xOffset;
            this.player.yPosition += yOffset;

            labyrinth[player.yPosition][player.xPosition].typeCell = TypeCell.PLAYER;

            this.panel.repaint();

            if(this.player.yPosition == size - 1) {

                JOptionPane.showMessageDialog(frame, "Вы выиграли!\nПереход на следующий уровень.", "Победа",
                        JOptionPane.INFORMATION_MESSAGE);
                        size += 2;
                level++;
                frame.getContentPane().remove(panel);
                newLevel();

            }

        }

    }

}
