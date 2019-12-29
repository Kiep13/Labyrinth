package generation;

public class Cell
{

    public TypeCell typeCell;
    public int xCoordinate;
    public int yCoordinate;

    public Cell(TypeCell typeCell, int xCoordinate, int yCoordinate) {

        this.typeCell = typeCell;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }

    public Cell(int xCoordinate, int yCoordinate) {

        this.typeCell = null;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }

    @Override
    public String toString() {

        return this.typeCell + " " + this.xCoordinate + "." + this.yCoordinate;

    }
}
