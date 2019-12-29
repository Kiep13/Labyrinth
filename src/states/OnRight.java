package states;

import generation.TypeCell;
import main.Player;

public class OnRight extends States
{

    public OnRight(Player player) {
        super(player);
    }

    @Override
    public boolean goRight(TypeCell cell) {
        return super.getRight(cell);
    }
}
