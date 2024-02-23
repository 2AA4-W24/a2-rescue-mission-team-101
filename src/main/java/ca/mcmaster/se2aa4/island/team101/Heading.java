package ca.mcmaster.se2aa4.island.team101;

public class Heading {
    private enum Directions{
        NORTH, EAST, SOUTH, WEST
    }

    // later initialize this to whatever is read from initialization
    // not sure what format it reads it in might have to use a method to 
    // pick the corresponding enum value to whatever is read from the json
    private Directions fwd;
    private JSONResponse response;

    public Heading(String initialHeading){
        setHeading(initialHeading);
    }

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

    public void setHeading(String heading){
        switch (heading) {
            case "N":
                fwd = Directions.NORTH;
            case "E":
                fwd = Directions.EAST;
            case "S":
                fwd = Directions.SOUTH;
            default:
                fwd = Directions.WEST;
        }
    }
}
