package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);
    private Integer charge;
    private AirDecision nextMove;
    private Compass compass;

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        this.charge = initializer.getBatteryLevel();
        this.nextMove = new AirDecision(this);
        this.compass = new Compass(initializer.getDirection());
    }

    @Override
    public void setNextMove(){
        nextMoveStr = nextMove.decide();
    }

    // should sweep and update everything like heading battery etc
    @Override
    public void update(Response response){ 
        setCharge(response.getCost());
    }

    public Compass getCompass(){
        return compass;
    }

    // Battery stuff
    public Integer getCharge(){
        return charge;
    }

    public void setCharge(Integer cost){
        charge -= cost;
        logger.info(" = {}", charge);
        if (charge <= 0){
            logger.info("*** Drone is dead. Womp Womp.");
            charge = 0;
        }
    }

}
