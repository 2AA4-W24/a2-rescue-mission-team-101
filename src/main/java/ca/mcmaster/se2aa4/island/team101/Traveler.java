package ca.mcmaster.se2aa4.island.team101;

public interface Traveler {
    void update();
    Command getNextMove();
    String latestType();
    Response getLatestResponse();
    Compass getCompass();
}
