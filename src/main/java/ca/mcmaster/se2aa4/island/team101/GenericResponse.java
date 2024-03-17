package ca.mcmaster.se2aa4.island.team101;

public class GenericResponse {
    protected final int cost;
    protected final String status;

    public GenericResponse(int cost, String status) {
        this.cost = cost;
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }
}