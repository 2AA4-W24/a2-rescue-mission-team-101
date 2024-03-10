package ca.mcmaster.se2aa4.island.team101;

public class Compass {

    private String direction;

    protected static final String NORTH = "N";
    protected static final String EAST = "E";
    protected static final String SOUTH = "S";
    protected static final String WEST = "W";
    private static int y=0;
    private static int x=0;

    /*
    public Compass(String init_direction){      // INITIALIZE HEADING HERE
        this.direction = init_direction;
    }
    */

    public String left(){
        switch (direction){
            case NORTH:
                direction = WEST;
                x--;
            case SOUTH:
                direction = EAST;
                x++;
            case EAST:
                direction = NORTH;
                y++;
            case WEST:
                direction = SOUTH;
                y--;
        }
        return direction;
    }

    public String right(){
        switch (direction){
            case NORTH:
                direction = EAST;
                x++;
            case SOUTH:
                direction = WEST;
                x--;
            case EAST:
                direction = SOUTH;
                y--;
            case WEST:
                direction = NORTH;
                y++;
        }
        return direction;
    }

    public String forward(){
        return direction;
    }
}
