package main;

import generation.Cell;
import generation.TypeCell;

import javax.swing.*;
import java.awt.*;

public class LPanel extends JPanel
{

    Cell[][] map;
    int size;

    public LPanel(Cell[][] map, int size) {

        this.map = map;
        this.size = size;

    }

    public void paintComponent(Graphics g){

        g.setColor(Color.WHITE);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        g.setColor(Color.BLACK);

        int width = this.getWidth();
        int height = this.getHeight();

        int xBlock = (width / (size + 2));
        int yBlock = (height / (size + 2));

        int xStart = xBlock;
        int yStart = yBlock;

        for(int i = 0; i < size; i++) {


            for(int j = 0; j < size; j++) {

                Cell cell = map[i][j];

                if(cell.typeCell == TypeCell.WALL) {

                    int x = xStart + (j*xBlock);
                    int y = yStart + (i*yBlock);
                    g.fillRect(x,y,xBlock, yBlock);

                }

                if(cell.typeCell == TypeCell.PLAYER) {

                    g.setColor(Color.GREEN);
                    int x = xStart + (j*xBlock);
                    int y = yStart + (i*yBlock);
                    g.fillOval(x,y,xBlock, yBlock);
                    g.setColor(Color.BLACK);

                }

            }

        }

    }

}
