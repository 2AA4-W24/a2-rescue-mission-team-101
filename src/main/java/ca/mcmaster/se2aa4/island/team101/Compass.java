package ca.mcmaster.se2aa4.island.team101;
import java.awt.Point;

public class Compass {
    private String direction;
    private Point position;

    protected static final String NORTH = "N";
    protected static final String EAST = "E";
    protected static final String SOUTH = "S";
    protected static final String WEST = "W";
    
    public Compass(String init_direction){      
        this.direction = init_direction;
        position = new Point(0,0);
    }

    private Point turn(Point p, String newHeading){
        advance(p);
        direction = newHeading;
        advance(p);
        return p;
    }
    public void updateHeading(String newDirection){
        direction = newDirection;
    }

    private Point advance(Point p){
        switch(direction){
            case NORTH:
                p.y--;
                break;
            case EAST:
                p.x++;
                break;
            case SOUTH:
                p.y++;
                break;
            default:
                p.x--;
                break;
        }
        return p;
    }

    public void turn(String newHeading){
        position = turn(position, newHeading);
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

    public void advance(){
        position = advance(position);
    }

    public Point getPosition(){
        return position;
    }

    public String getDirection(){
        return direction;
    }

}
