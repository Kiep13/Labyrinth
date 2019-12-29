package states;

import generation.TypeCell;
import main.Player;

public class OnDown extends States
{

    public OnDown(Player player){
        super(player);
    }

    @Override
    public boolean goDown(TypeCell cell) {
        return super.getDown(cell);
    }
}
