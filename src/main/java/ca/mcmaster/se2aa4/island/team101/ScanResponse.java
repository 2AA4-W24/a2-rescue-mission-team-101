package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONArray;

public class ScanResponse extends GenericResponse {
    private final JSONArray biomes;
    private final JSONArray creeks;
    private final JSONArray sites;

    public ScanResponse(int cost, String status, JSONArray biomes, JSONArray creeks, JSONArray sites) {
        super(cost, status);
        this.biomes = biomes;
        this.creeks = creeks;
        this.sites = sites;
    }

    public JSONArray getBiomes() {
        return biomes;
    }

    public JSONArray getCreeks() {
        return creeks;
    }

    public JSONArray getSites() {
        return sites;
    }
}
