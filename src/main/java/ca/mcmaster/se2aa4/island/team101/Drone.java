package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);

    
    private Integer charge;

    private Decision nextMove; 

    private Compass compass; // this has the position
    private AreaMap map;

    private String lastCommand;

    private Response<?> lastResponse;

    public Drone(JSONInitialization initializer) {
        this.charge = initializer.getBatteryLevel();
        this.compass = new Compass(initializer.getDirection());
        this.lastCommand = "echo";
        this.map = new AreaMap(); // Initialize the map
        this.nextMove = new AirDecision(this);
    }


    @Override
    public void update(Response<?> response) {

        nextMove.updateResponse(typedResponse);

        // Set the charge based on the response cost
        setCharge(typedResponse.getCost());
        logger.info("COST OF ACTION: " + typedResponse.getCost());
        
        // Update the map only if it was a scan response
        if (lastCommand.equals("scan")) {
            logger.info("*******************LAST COMMAND WAS A SCAN**************************");
            ScanResponse scanResponse = (ScanResponse) typedResponse;
            map.updateMap(compass.getPosition(), scanResponse);
        }
        
        //compass.turn(lastCommand);
    }

    public String droneNextMove(){
        return nextMove.decide();
    }

    public Compass getCompass(){
        return compass;
    }

    private void setCharge(Integer cost) {
        logger.info("BATTERY WAS: " + charge);
        charge -= cost;
        logger.info("BATTERY HAS BEEN CHANGED TO: " + charge);
        logger.info("Battery level: {}", charge);
        if (charge <= 0) {
            logger.info("*** Drone is out of battery. ***");
            charge = 0;
        }
    }
}
