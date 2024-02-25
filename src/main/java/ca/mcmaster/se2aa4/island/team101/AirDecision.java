package ca.mcmaster.se2aa4.island.team101;

public class AirDecision extends Decision {
    private Drone drone;
    // use getters from drone to get other relevant info/objects for decision logic
    // should only need emergency detector and battery through drone
    // commands cover everything else i think

    public AirDecision(Drone drone) {
        super(drone);
    }

    @Override
    public String decide() {
        // commands from decision can be used here with FLY, ECHO, etc.
        // check the emergencydetector methods to see if its gonna go MIA, and why, and adjust accordingly
        return "fly";
    }
}

