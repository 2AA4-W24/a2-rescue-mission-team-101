package ca.mcmaster.se2aa4.island.team101;

public interface Controller {
    Command getNextMove();
    void transition();
}
