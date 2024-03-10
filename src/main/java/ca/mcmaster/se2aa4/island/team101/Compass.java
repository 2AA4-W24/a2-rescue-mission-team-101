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

    // this is for updating the internal coordinate when you turn

    // this is odd because of the way it turns; it doesnt just turn
    // on the spot, it kinda ends up diagonal to wherever it was.
    // it goes fwd, then turns on the spot, then fwd one more time.
    private Point turn(Point p, String newHeading){
        // maybe add in here some kind of protection against a bad command that would flip it

        // goes one fwd from where it was
        advance(p);
        // change direction to whatever 
        direction = newHeading;
        // then goes one forward from there
        advance(p);
        return p;
    }

    // this is for updating the internal coordinate when u fly forward
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
