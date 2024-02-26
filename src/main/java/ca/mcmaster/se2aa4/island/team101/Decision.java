package ca.mcmaster.se2aa4.island.team101;

public abstract class Decision {
    protected Traveler traveler;
    protected EmergencyDetector emergency;

    // so that the command strings can be easily changed if the string to do whichever action
    // changes, might need to add more not sure all the available commands
    protected static final String FLY = "fly";
    protected static final String STOP = "stop";
    protected static final String HEADING = "heading";
    protected static final String ECHO = "echo";
    protected static final String SCAN = "scan";

    public Decision(Traveler traveler) {
        this.traveler = traveler;
    }

    //different logic for either air or land
    public abstract String decide();

}
