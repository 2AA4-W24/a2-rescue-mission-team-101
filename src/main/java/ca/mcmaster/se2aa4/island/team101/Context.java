package ca.mcmaster.se2aa4.island.team101;

public interface Context {
    void setState(State state);
    String getNextState();
    Command getNextMove();
}
