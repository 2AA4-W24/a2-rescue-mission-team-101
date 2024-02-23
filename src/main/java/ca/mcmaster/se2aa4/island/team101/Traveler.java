package ca.mcmaster.se2aa4.island.team101;

public abstract class Traveler {
    protected Heading heading = new Heading();
    protected Radar radar = new Radar();
    protected PhotoScanner scanner = new PhotoScanner();
    protected String nextMoveStr;
    protected Decision nextMove; // is air or land in concrete classes

    public Traveler(){}

    public String getNextMove(){
        return nextMoveStr; 
    }

    public abstract void setNextMove();

    public Heading getHeading(){
        return heading;
    }

    public Radar getRadar(){
        return radar;
    }

    public PhotoScanner getScanner(){
        return scanner;
    }
}
