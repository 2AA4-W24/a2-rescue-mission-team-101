package ca.mcmaster.se2aa4.island.team101;

public class GenericResponse {
    protected final int cost;
    protected final String status;
    private String type;
    private Boolean hasOnlyOcean;

    public GenericResponse(int cost, String status) {
        this.cost = cost;
        this.status = status;
        this.type = "fly";// THIS NEEDS TO CHANGE BC GENERIC ISNT ALWAYS FLY JUST HARD CODING FOR NOW
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public String getType(){
        return type;
    }

    public Boolean hasOnlyOcean(){
        return hasOnlyOcean;
    }

}