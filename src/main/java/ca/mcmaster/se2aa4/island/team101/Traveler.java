package ca.mcmaster.se2aa4.island.team101;

public abstract class Traveler {
    
    protected Initialization initializer;
    protected String nextMoveStr;
    protected Decision nextMove; // is air or land in concrete classes

    public Traveler(){}

    public String getNextMove(){
        return nextMoveStr; 
    }

    public abstract void setNextMove();

    public abstract void update(Response scan);

}
