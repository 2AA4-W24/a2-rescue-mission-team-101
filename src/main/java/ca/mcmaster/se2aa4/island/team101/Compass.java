package ca.mcmaster.se2aa4.island.team101;
import java.awt.Point;


public class Compass {
    private String direction;
    private Point position;

    protected static final String NORTH = "N";
    protected static final String EAST = "E";
    protected static final String SOUTH = "S";
    protected static final String WEST = "W";
    
    public Compass(String initDirection){      
        this.direction = initDirection;
        position = new Point(0,0);
    }

    private void turn(String newHeading){
        advance();
        direction = newHeading;
        advance();
    }

    public void advance(){
        switch(direction){
            case NORTH:
                position.y--;
                break;
            case EAST:
                position.x++;
                break;
            case SOUTH:
                position.y++;
                break;
            default:
                position.x--;
                break;
        }
    }

    public void turnRight(){
        switch(direction){
            case NORTH:
                turn(EAST);
                break;
            case EAST:
                turn(SOUTH);
                break;
            case SOUTH:
                turn(WEST);
                break;
            default:
                turn(NORTH);
                break;
        }
    }

    public void turnLeft(){
        switch(direction){
            case NORTH:
                turn(WEST);
                break;
            case EAST:
                turn(NORTH);
                break;
            case SOUTH:
                turn(EAST);
                break;
            default:
                turn(SOUTH);
                break;
        }
    }

    public String getRight(){
        switch(direction){
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            default:
                return NORTH;
        }
    }

    public String getLeft(){
        switch(direction){
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            default:
                return SOUTH;
        }
    }

    public Point getPosition(){
        return position;
    }

    public String getDirection(){
        return direction;
    }

}
