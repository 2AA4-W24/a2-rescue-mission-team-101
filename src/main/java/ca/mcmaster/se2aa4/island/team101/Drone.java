package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {

    private final Logger logger = LogManager.getLogger(Drone.class);
    private Integer charge;
    private Decision nextMove; 
    private Compass compass;
    private AreaMap map;
    private String lastCommand;
    private Response<?> lastResponse;

    public Drone(JSONInitialization initializer) {
        this.initializer = initializer;
        this.charge = initializer.getBatteryLevel();
        this.compass = new Compass(initializer.getDirection());
        this.lastCommand = "echo";
        this.map = new AreaMap(); // Initialize the map
        this.nextMove = new AirDecision(this);
    }

    @Override
    public void setNextMove(String command) {
        lastCommand = command;
    }

    @Override
    public String getLastMove() {
        return lastCommand;
    }

    @Override
    public void update(Response<?> response) {
        lastResponse = response;

        // Handle the response based on the command type
        GenericResponse typedResponse = response.handleResponse();
        nextMove.updateResponse(typedResponse);

        // Set the charge based on the response cost
        setCharge(typedResponse.getCost());
        
        // Update the map only if it was a scan response
        if (lastCommand.equals("scan")) {
            ScanResponse scanResponse = (ScanResponse) typedResponse;
            map.updateMap(compass.getPosition(), scanResponse);
        }
        
        compass.turn(lastCommand);
    }

    public String droneNextMove(){
        return nextMove.decide();
    }
    public String getLastType(){
        return nextMove.getType();
    }

    public Response<?> getResponse() {
        return lastResponse;
    }

    public Integer getCharge() {
        return charge;
    }

    public Compass getCompass(){
        return compass;
    }

    private void setCharge(Integer cost) {
        charge -= cost;
        logger.info("Battery level: {}", charge);
        if (charge <= 0) {
            logger.info("*** Drone is out of battery. ***");
            charge = 0;
        }
    }
}
