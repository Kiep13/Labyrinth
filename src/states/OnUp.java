package states;

import generation.TypeCell;
import main.Player;

public class OnUp extends States
{

    public OnUp(Player player) {
        super(player);
    }
    @Override
    public boolean goUp(TypeCell cell) {
        return super.getUp(cell);
    }

}
