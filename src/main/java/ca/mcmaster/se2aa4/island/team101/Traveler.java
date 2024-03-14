package ca.mcmaster.se2aa4.island.team101;

public abstract class Traveler {
    
    protected Initialization initializer;
    protected String nextMoveStr;
    protected Decision nextMove; // is air or land in concrete classes

    public Traveler(){}

    public String getLastMove(){
        return nextMoveStr; 
    }

    public abstract void setNextMove(String command);

    public abstract void update(Response scan);

}
