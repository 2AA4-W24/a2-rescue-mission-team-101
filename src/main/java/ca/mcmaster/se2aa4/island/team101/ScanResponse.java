package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONArray;

public class ScanResponse extends GenericResponse {
    private final JSONArray biomes;
    private final JSONArray creeks;
    private final JSONArray sites;
    private final String type;

    public ScanResponse(int cost, String status, JSONArray biomes, JSONArray creeks, JSONArray sites) {
        super(cost, status);
        this.biomes = biomes;
        this.creeks = creeks;
        this.sites = sites;
        this.type = "scan";
    }

    public JSONArray getBiomes() {
        return biomes;
    }
    @Override
    public Boolean hasOnlyOcean() {
        for (int i = 0; i < biomes.length(); i++) {
            if (!("OCEAN".equals(biomes.optString(i)))) {
                return false;
            }
        }
        return true;
    }

    public JSONArray getCreeks() {
        return creeks;
    }

    public JSONArray getSites() {
        return sites;
    }

    public String getType(){
        return type;
    }
}
