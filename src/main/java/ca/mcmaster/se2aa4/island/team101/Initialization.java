package ca.mcmaster.se2aa4.island.team101;

public interface Initialization<T> {
    String toString();
    void initialize(String data);
    T getObject();
}
