package ca.mcmaster.se2aa4.island.team101;


public class Drone implements Traveler{

    private Integer charge;
    private AreaMap map;
    private Response latestResponse;  
    private Compass compass;
    private Controller controller;
    private Command latestCommand;


    public Drone(Integer charge, AreaMap map, Compass compass){
        this.charge = charge;
        this.map = map;
        this.compass = compass;
        this.controller = new DroneController(this);
    }

    @Override
    public void update(Response response){
        latestResponse = response;
        map.updateMap(compass.getPosition(), response, latestCommand);
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
    }

    public Integer getCharge(){
        return charge;
    }

    public String getClosestCreek(){
        return map.findClosestCreek();
    }

}