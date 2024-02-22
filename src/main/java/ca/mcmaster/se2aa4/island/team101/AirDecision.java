package ca.mcmaster.se2aa4.island.team101;

public class AirDecision extends Decision {
    private Drone drone;
    // use getters from drone to get other relevant info/objects for decision logic
    // and use setters to change them i guess

    public AirDecision(Drone drone) {
        super(drone);
        this.drone = drone;
    }

    @Override
    public String decide() {
        Heading heading = drone.getHeading(); // stuff like this to get ur relevant info
        // Logic specific to drone, use all the info from the drone objet here
        // not sure how to return strings for turning...maybe need to change heading logic to work
        // with however you turn in this, if there's a command to turn n/e/s/w, instead of DIY turning
        // then those commands could be an option to return from here + add to String list in decision
        return "fly";
    }
}

