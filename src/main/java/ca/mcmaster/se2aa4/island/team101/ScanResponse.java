package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;
import org.json.JSONArray;

public class ScanResponse extends Response{

    private JSONArray biomes;
    private JSONArray creeks;
    private JSONArray sites;

    public ScanResponse(int cost, JSONObject extras, String status){
        this.cost = cost;
        this.extras = extras;
        this.status = status;
        this.biomes = extras.optJSONArray("biomes");
        this.creeks = extras.optJSONArray("creeks");
        this.sites = extras.optJSONArray("sites");
    }

    @Override
    public int getCost(){
        return this.cost;
    }

    public JSONArray getBiomes(){
        return this.biomes;
    }

    public JSONArray getCreeks(){
        return this.creeks;
    }

    public JSONArray getSites(){
        return this.sites;
    }

    public Boolean isAllOcean() {
        for (int i = 0; i < biomes.length(); i++) {
            if (!("OCEAN".equals(biomes.optString(i)))) {
                return false;
            }
        }
        return true;
    }
    
}