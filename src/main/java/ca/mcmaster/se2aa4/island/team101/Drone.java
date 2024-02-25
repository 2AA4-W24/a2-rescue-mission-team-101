package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone extends Traveler {
    private final Logger logger = LogManager.getLogger();

    private Battery battery;
    private EmergencyDetector emergency;
    private AirDecision nextMove;

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        battery = new Battery(initializer.getBatteryLevel()); //drone battery initialized to initial battery
        //emergency = new EmergencyDetector(initializer.getStatus()); // emergency detector initialized to hold initial status

        this.nextMove = new AirDecision(this);

    }

    public void update(JSONResponse response){
        battery.setCharge(response.getCost());
    }

    @Override
    public void setNextMove(){
        // should do the algorithm however by calling decide on the airdecision
        // then this just assigns it to the nextmovestr which is returned in the getNextMove
        // in traveler parent
        nextMoveStr = nextMove.decide();
    }

    public Battery getBattery(){
        return battery;
    }

    public EmergencyDetector getEmergency(){
        return emergency;
    }

    // how should we return the closest creek? as a point?

}
