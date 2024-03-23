package ca.mcmaster.se2aa4.island.team101;
import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Drone{

    private Integer charge;
    private AreaMap map;
    private String latestType = ECHO;
    private Compass compass;
    private DroneController controller;

    public Drone(Integer charge, AreaMap map, Compass compass){
        this.charge = charge;
        this.map = map;
        this.compass = compass;
    }

    public void update(Response response){
        latestType = response.getType();
        map.updateMap(compass.getPosition(), response);
        setCharge(response.getCost());
    }

    public String getNextMove(){
        return controller.getNextMove(); // will call on decision logic from controller to each state
        // returns the decision string
    }

    // for decision
    public String latestType(){
        return latestType;
    }

    private void setCharge(Integer cost) {
        charge -= cost;
        if (charge <= 0) {
            charge = 0;
        }
    }

}