package ca.mcmaster.se2aa4.island.team101;

public interface Response<T> {
    String toString();
    T getExtraInfo();
    Integer getCost();
}
