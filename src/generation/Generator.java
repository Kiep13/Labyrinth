package generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator
{

    public Cell[][] generateSimpleTable(int size) {

        Cell[][] table = new Cell[size][size];
        List<Cell> stack = new ArrayList();

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                if((i % 2 != 0  && j % 2 != 0) && (i < size-1 && j < size-1)) {
                    table[i][j] = new Cell(TypeCell.CELL,j, i);
                }
                else table[i][j] = new Cell(TypeCell.WALL,j, i);

            }


        }

        Cell currentCell = new Cell(1,1);
        table[currentCell.xCoordinate][currentCell.yCoordinate].typeCell = TypeCell.VISITED;
        Cell neighborCell;

        Random random = new Random();
        int randNum;

        do {
            List<Cell> neighbors = getNeighbors(size, table, currentCell, 2);

            if(neighbors.size() != 0) {

                randNum = random.nextInt(neighbors.size());
                neighborCell = neighbors.get(randNum);
                stack.add(currentCell);
                removeWall(currentCell, neighborCell, table);
                currentCell = neighborCell;
                table[currentCell.xCoordinate][currentCell.yCoordinate].typeCell = TypeCell.VISITED;

            } else if(stack.size() > 0) {

                currentCell = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);

            } else {

                List<Cell> unvisitedCells = getUnvisitedCells(size, table);
                randNum = random.nextInt(unvisitedCells.size());
                currentCell = unvisitedCells.get(randNum);

            }

        } while(hasUnvisited(size, table));

        table[0][1].typeCell = TypeCell.PLAYER;
        table[size-1][size-2].typeCell = TypeCell.VISITED;

        return table;

    }

    private void removeWall(Cell currentCell, Cell neighborCell, Cell[][] maze) {

        int xDiff = neighborCell.xCoordinate - currentCell.xCoordinate;
        int yDiff = neighborCell.yCoordinate - currentCell.yCoordinate;
        int addX, addY;

        addX = (xDiff != 0) ? (xDiff / Math.abs(xDiff)) : 0;
        addY = (yDiff != 0) ? (yDiff / Math.abs(yDiff)) : 0;

        Cell target = new Cell(currentCell.xCoordinate + addX, currentCell.yCoordinate + addY);

        maze[target.yCoordinate][target.xCoordinate].typeCell = TypeCell.VISITED;

    }

    private List<Cell> getNeighbors(int size, Cell[][] map, Cell cell, int distance) {

        int x = cell.xCoordinate, y = cell.yCoordinate;
        Cell up = new Cell(x, y - distance);
        Cell right = new Cell(x + distance, y);
        Cell down = new Cell(x, y + distance);
        Cell left = new Cell(x - distance, y);

        List<Cell> array = new ArrayList<Cell>();
        array.add(down);
        array.add(right);
        array.add(up);
        array.add(left);

        List<Cell> unvisitedCells = new ArrayList<Cell>();

        for (Cell currentCell  : array) {

            if (currentCell .xCoordinate > 0 && currentCell .xCoordinate < size && currentCell .yCoordinate > 0 && currentCell .yCoordinate < size) {

                TypeCell typeCellCurrent = map[currentCell .xCoordinate][currentCell .yCoordinate].typeCell;

                if (typeCellCurrent != TypeCell.WALL && typeCellCurrent != TypeCell.VISITED) {

                    unvisitedCells.add(currentCell);

                }

            }

        }

        return unvisitedCells;
    }

    private List<Cell> getUnvisitedCells(int size,Cell [][] maze) {

        List<Cell> unvisitedCells = new ArrayList();
        TypeCell typeCellCurrent;

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                typeCellCurrent = maze[i][j].typeCell;

                if(typeCellCurrent != TypeCell.WALL && typeCellCurrent != TypeCell.VISITED) {

                    unvisitedCells.add(maze[i][j]);

                }

            }

        }

        return unvisitedCells;
    }

    private boolean hasUnvisited(int size, Cell [][] maze) {

        TypeCell typeCellCurrent;

        for(int i = 0; i < size; i++) {

            for(int j = 0; j < size; j++) {

                typeCellCurrent = maze[i][j].typeCell;

                if(typeCellCurrent != TypeCell.WALL && typeCellCurrent != TypeCell.VISITED) {
                    return true;
                }

            }

        }

        return false;
    }

}
