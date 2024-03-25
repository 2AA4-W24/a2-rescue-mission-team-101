package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DroneContext implements Context{

    private State droneState;

    private final Logger logger = LogManager.getLogger();

    // make getters and setters instead of public once you know which ones to keep and which to kill
    public int distanceToEdge = 0, eta = 0, scanCount = 0, turn = 0, stage = 0, edge = 0, distanceToLand = 0, uTurnStage = 0;
    public Boolean facingLand = false, atLand = false, scanComplete = false, lastTurnRight = true, activateUTurn = false, fly = false, left = false;
    public String newDirection, faceIslandAgain;

    public DroneContext(){}

    @Override
    public void setState(State state){
        this.droneState = state;
    }

    @Override
    public String getNextState(){
        return this.droneState.getNextState();
    }

    @Override
    public Command getNextMove(){
        return this.droneState.getNextMove();
    }

}