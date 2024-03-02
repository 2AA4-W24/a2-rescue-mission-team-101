package ca.mcmaster.se2aa4.island.team101;

public abstract class Decision {
    protected Traveler traveler;
    protected EmergencyDetector emergency;

    // Don't wanna hard code the strings in for parameter passing
    // maybe kinda odd to have these here idk could be better organized????
    protected static final String NORTH = "N";
    protected static final String EAST = "E";
    protected static final String SOUTH = "S";
    protected static final String WEST = "W";

    // so that the command strings can be easily changed if the string to do whichever action
    // changes, might need to add more not sure all the available commands

    public Decision(Traveler traveler) {
        this.traveler = traveler;
    }

    //different logic for either air or land
    public abstract String decide();

}
