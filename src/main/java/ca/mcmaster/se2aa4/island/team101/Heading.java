package ca.mcmaster.se2aa4.island.team101;

public class Heading {
    private enum Directions{
        NORTH, EAST, SOUTH, WEST
    }

    // later initialize this to whatever is read from initialization
    private Directions fwd = Directions.EAST;

    /**
     * Turns the traveler right
     */
    protected void turnRight() {
        fwd = Directions.values()[(fwd.ordinal() + 1) % 4];
    }

    /**
     * Turns the traveler left
     */
    protected void turnLeft() {
        fwd = Directions.values()[(fwd.ordinal() + 3) % 4];
    }

    /**
     * Traveler makes a u-turn
     */
    protected void uTurn() {
        turnRight();
        turnRight();
    }
}
