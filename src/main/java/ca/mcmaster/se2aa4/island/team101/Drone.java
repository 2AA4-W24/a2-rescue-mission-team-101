package ca.mcmaster.se2aa4.island.team101;

public class Drone{

    private Integer charge;
    private AreaMap map;
    private Response latestResponse;
    private Compass compass;
    private DroneController controller;

    public Drone(Integer charge, AreaMap map, Compass compass){
        this.charge = charge;
        this.map = map;
        this.compass = compass;
        this.controller = new DroneController(this);
    }

    public void update(Response response){
        latestResponse = response;
        map.updateMap(compass.getPosition(), response);
        setCharge(response.getCost());
    }

    public String getNextMove(){
        return controller.getNextMove();
    }

    public String latestType(){
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