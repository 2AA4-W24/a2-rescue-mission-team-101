package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DroneContext{

    private State droneState;

    private final Logger logger = LogManager.getLogger();


    // make getters and setters instead of public once you know which ones to keep and which to kill
    public int distanceToEdge = 0, eta = 0, scanCount = 0, turn = 0, stage = 0, edge = 0, distanceToLand;
    public Boolean facingLand = false, atLand = false, scanComplete = false, lastTurnRight = false, activateUTurn = false, fly = false, left = false;
    public String newDirection, faceIslandAgain;

    public DroneContext(){}

    public void setState(State state){
        this.droneState = state;
    }

    public String getNextState(){
        logger.info("****************IN DRONE CONTEXT GET NEXT STATE******************");
        return this.droneState.getNextState();
    }

    public String getNextMove(){
        logger.info("getnextmove" + this.droneState.getNextMove().toString());
        return this.droneState.getNextMove();
    }

}