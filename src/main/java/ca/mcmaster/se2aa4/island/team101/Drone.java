package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);
    private Integer charge;
    private AirDecision nextMove;
    private Compass compass;
    private AreaMap map;
    private String lastCommand; 

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        this.charge = initializer.getBatteryLevel();
        this.nextMove = new AirDecision(this); // this is weird idk if there's another way
        this.compass = new Compass(initializer.getDirection());
        this.lastCommand = "echo";
    }

    @Override
    public void setNextMove(String command){
        lastCommand = command;
    }

    @Override
    public String getLastMove(){
        return lastCommand;
    }

    // should sweep and update everything like heading battery etc
    // we might wanna scan every tile.
    @Override
    public void update(Response response){ 
        setCharge(response.getCost());
        // only update the map if it was a scanresponse
        switch (lastCommand) {
            case "scan":
                // the Point coordinate of the drone is held and maintained in the compass. 
                // it grabs it in here to update the map
                map.updateMap(compass.getPosition(), (ScanResponse)response);
                break;
            case "fly":
                compass.advance();
            case "echo":
                response = (EchoResponse)response;
            default:
                break;
        }
        // need to update the compass somehow 
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
    
    public Compass getCompass(){
        return compass;
    }

}