package ca.mcmaster.se2aa4.island.team101;

public interface Initialization<T> {
    String toString();
    T getObject();
    void initialize(String data);
}
