package states;

import generation.TypeCell;
import main.Player;

public abstract class States
{

    public Player player;

    public States(Player player) {
        this.player = player;
    }

    public boolean getUp(TypeCell cell) {
        return cell != TypeCell.WALL;
    }

    public boolean getRight(TypeCell cell) {
        return cell != TypeCell.WALL;
    }

    public boolean getDown(TypeCell cell) {
        return cell != TypeCell.WALL;
    }

    public boolean getLeft(TypeCell cell) {
        return cell != TypeCell.WALL;
    }

    public boolean goLeft(TypeCell cell) {

        if(getLeft(cell)) {
            player.setState(new OnLeft(player));
            return true;
        }

        return false;
    }

    public boolean goRight(TypeCell cell) {

        if(getRight(cell)) {
            player.setState(new OnRight(player));
            return true;
        };

        return false;
    }

    public boolean goDown(TypeCell cell) {

        if(getDown(cell)) {
            player.setState(new OnDown(player));
            return true;
        }

        return false;
    }

    public boolean goUp(TypeCell cell) {

        if(getUp(cell)) {
            player.setState(new OnUp(player));
            return true;
        }

        return false;
    }

}
