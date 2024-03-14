package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);
    private Integer charge;
    private AirDecision nextMove;
    private Compass compass;
    private AreaMap map;

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        this.charge = initializer.getBatteryLevel();
        this.nextMove = new AirDecision(this); // this is weird idk if there's another way
        this.compass = new Compass(initializer.getDirection());
    }

    @Override
    public void setNextMove(){
        nextMoveStr = nextMove.decide();
    }

    // should sweep and update everything like heading battery etc
    // we might wanna scan every tile.
    @Override
    public void update(Response response){ 
        setCharge(response.getCost());
        // only update the map w it if it was a scanresponse
        if (response instanceof ScanResponse) {
            // the Point coordinate of the drone is held and maintained in the compass. 
            // it grabs it in here to update the map
            map.updateMap(compass.getPosition(), (ScanResponse)response);
        }
        // need to update the compass somehow. 
    }

    // Battery stuff
    public Integer getCharge(){
        return charge;
    }

    private void setCharge(Integer cost){
        charge -= cost;
        logger.info(" = {}", charge);
        if (charge <= 0){
            logger.info("*** Drone is dead. Womp Womp.");
            charge = 0;
        }
    }

}
