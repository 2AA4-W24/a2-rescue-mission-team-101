package ca.mcmaster.se2aa4.island.team101;

public interface CommandReceiver {

    void fly();
    void stop();
    void heading(String direction);
    void echo(String direction);
    void scan();
    String getType();

    @Override
    String toString();
}
