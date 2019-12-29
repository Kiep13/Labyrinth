package main;

import generation.TypeCell;
import states.OnDown;
import states.States;

public class Player
{

    private States state;
    public int xPosition;
    public int yPosition;

    public Player() {

        this.xPosition = 1;
        this.yPosition = 0;
        this.state = new OnDown(this);

    }

    public void setState(States state) {
        this.state = state;
    }

    public boolean goUp(TypeCell cell) {
        return state.goUp(cell);
    }

    public boolean goDown(TypeCell cell) {
        return state.goDown(cell);
    }

    public boolean goLeft(TypeCell cell) {
        return state.goLeft(cell);
    }

    public boolean goRight(TypeCell cell) {
        return state.goRight(cell);
    }

    @Override
    public String toString() {
        return "Игрок может двигаться " + state.toString();
    }
}
