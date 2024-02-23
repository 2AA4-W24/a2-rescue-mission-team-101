package ca.mcmaster.se2aa4.island.team101;

public class Heading {
    private enum Directions{
        NORTH, EAST, SOUTH, WEST
    }

    // later initialize this to whatever is read from initialization
    // not sure what format it reads it in might have to use a method to 
    // pick the corresponding enum value to whatever is read from the json
    private Directions fwd = Directions.EAST;

    public Heading(){}

    protected void turnRight() {
        fwd = Directions.values()[(fwd.ordinal() + 1) % 4];
    }

    protected void turnLeft() {
        fwd = Directions.values()[(fwd.ordinal() + 3) % 4];
    }

    protected void uTurn() {
        turnRight();
        turnRight();
    }
}
