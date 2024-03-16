package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScanHandler implements ResponseHandler<ScanResponse> {
    @Override
    public ScanResponse handle(JSONObject response, JSONObject extras) {
        int cost = extras.optInt("cost");
        String status = response.optString("status");
        JSONArray biomes = extras.optJSONArray("biomes");
        JSONArray creeks = extras.optJSONArray("creeks");
        JSONArray sites = extras.optJSONArray("sites");
        return new ScanResponse(cost, status, biomes, creeks, sites);
    }
}