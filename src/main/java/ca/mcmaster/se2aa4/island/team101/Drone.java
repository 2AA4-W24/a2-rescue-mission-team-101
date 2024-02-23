package ca.mcmaster.se2aa4.island.team101;

public class Drone extends Traveler {
    private Battery battery;
    private EmergencyDetector emergency;
    private AirDecision nextMove;

    public Drone(JSONInitialization initializer){
        this.initializer = initializer;
        battery = new Battery(initializer.getBatteryLevel()); //drone battery initialized to initial battery
        heading = new Heading(initializer.getDirection()); // drone heading initialized to initial heading
        emergency = new EmergencyDetector(initializer.getStatus()); // emergency detector initialized to hold initial status

        this.nextMove = new AirDecision(this);
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

}
