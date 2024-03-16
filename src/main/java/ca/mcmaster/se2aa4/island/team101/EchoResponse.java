package ca.mcmaster.se2aa4.island.team101;

public class EchoResponse extends GenericResponse {
    private final int range;
    private final String found;

    public EchoResponse(int cost, String status, int range, String found) {
        super(cost, status);
        this.range = range;
        this.found = found;
    }

    public int getRange() {
        return range;
    }

    public String getFound() {
        return found;
    }
}
