package ca.mcmaster.se2aa4.island.team101;

public class Compass {

    private String direction;

    protected static final String NORTH = "N";
    protected static final String EAST = "E";
    protected static final String SOUTH = "S";
    protected static final String WEST = "W";

    /*
    public Compass(String init_direction){      // INITIALIZE HEADING HERE
        this.direction = init_direction;
    }
    */

    public String left(){
        switch (direction){
            case NORTH:
                direction = WEST;
            case SOUTH:
                direction = EAST;
            case EAST:
                direction = NORTH;
            case WEST:
                direction = SOUTH;
        }
        return direction;
    }

    public String right(){
        switch (direction){
            case NORTH:
                direction = EAST;
            case SOUTH:
                direction = WEST;
            case EAST:
                direction = SOUTH;
            case WEST:
                direction = NORTH;
        }
        return direction;
    }

    public String forward(){
        return direction;
    }
}
