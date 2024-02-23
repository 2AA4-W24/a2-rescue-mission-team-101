package ca.mcmaster.se2aa4.island.team101;

public class Drone extends Traveler {
    
    public Drone(){
        // instantiate as airdecision for drone
        this.nextMove = new AirDecision(this);
    }

    @Override
    public void setNextMove(){
        // should do the algorithm however by calling decide on the airdecision
        // then this just assigns it to the nextmovestr which is returned in the getNextMove
        // in traveler parent
        nextMoveStr = nextMove.decide();
    }

}
