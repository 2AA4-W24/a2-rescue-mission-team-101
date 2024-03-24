package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone{

    private Integer charge;
    private AreaMap map;
    private Response latestResponse;  
    private Compass compass;
    private DroneController controller;

    private final Logger logger = LogManager.getLogger();


    public Drone(Integer charge, AreaMap map, Compass compass){
        this.charge = charge;
        this.map = map;
        this.compass = compass;
        this.controller = new DroneController(this);
    }

    public void update(Response response){
        logger.info("****************IN DRONE UPDATE******************");
        logger.info("RESPONSE " + response);
        latestResponse = response;
        map.updateMap(compass.getPosition(), response);
        setCharge(response.getCost());
        controller.transition();

    }

    public String getNextMove(){
        logger.info("****************IN DRONE GETNsfhwfhishEXTMOVE******************");
        logger.info("****************IN DRONE GETNEXTMOVE******************");
        return controller.getNextMove();
    }

    public String getClosestCreek(){
        return map.FindClosestCreek();
    }

    public String latestType(){
        if (latestResponse == null){
            return "echo";
        }
        return latestResponse.getType();
    }

    public Response getLatestResponse(){
        return latestResponse;
    }
    
    public Compass getCompass(){
        return compass;
    }

    private void setCharge(Integer cost) {
        charge -= cost;
        if (charge <= 0) charge = 0;
    }

}