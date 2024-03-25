package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone implements Traveler{

    private Integer charge;
    private AreaMap map;
    private Response latestResponse;  
    private Compass compass;
    private DroneController controller;
    private Command latestCommand;

    private final Logger logger = LogManager.getLogger();

    public Drone(Integer charge, AreaMap map, Compass compass){
        this.charge = charge;
        this.map = map;
        this.compass = compass;
        this.controller = new DroneController(this);
    }

    @Override
    public void update(Response response){
        logger.info("RESPONSE " + latestResponse);
        latestResponse = response;
        map.updateMap(compass.getPosition(), response);
        setCharge(response.getCost());
        controller.transition();

    }

    @Override
    public Command getNextMove(){
        latestCommand = controller.getNextMove();
        return latestCommand;
    }

    @Override
    public String latestType(){
        if (latestCommand == null){
            return "echo";
        }
        return latestCommand.getType();
    }

    @Override
    public Response getLatestResponse(){
        return latestResponse;
    }
    
    @Override
    public Compass getCompass(){
        return compass;
    }

    private void setCharge(Integer cost) {
        charge -= cost;
        if (charge <= 0) charge = 0;
    }

}