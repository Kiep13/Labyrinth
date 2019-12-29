package states;

import generation.TypeCell;
import main.Player;

public class OnLeft extends States
{

    public OnLeft(Player player) {
        super(player);
    }

    @Override
    public boolean goLeft(TypeCell cell) {
        return super.getLeft(cell);
    }
}
