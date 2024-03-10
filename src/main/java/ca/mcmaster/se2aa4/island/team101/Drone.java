package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Point;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);
    private Integer charge;
    private AirDecision nextMove;
    private Compass compass;
    private Point position;

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        this.charge = initializer.getBatteryLevel();
        this.nextMove = new AirDecision(this);
        this.compass = new Compass(initializer.getDirection());
        this.position = new Point(0, 0);
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
