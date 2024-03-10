package ca.mcmaster.se2aa4.island.team101;
import java.util.List;
import org.json.JSONArray;
import java.util.ArrayList;

public class ScanResponse extends JSONResponse {

    public ScanResponse(String data) {
        super(data);
    }

    public List<String> getBiomes() {
        return toList(extras.getJSONArray("biomes"));
    }

    // intended to be used per tile so each tile can have max one creek (i assume)
    public String getCreek() {
        JSONArray creeksArray = extras.getJSONArray("creeks");
        if (creeksArray.length() > 0) {
            return creeksArray.getString(0);
        }
        return "";
    }

    // same deal i assume each tile can have max one site
    public String getSite() {
        JSONArray sitesArray = extras.getJSONArray("sites");
        if (sitesArray.length() > 0) {
            return sitesArray.getString(0);
        }
        return "";
    }

    // this is a lot of code for biomes which we dont currently use or care about
    // idk think abt if we really need it from the scan
    // although we should keep this, if creek and site can have multiple per scan
    // but i assume its only one per scan for those.
    private List<String> toList(JSONArray arr) {
        List<String> list = new ArrayList<>(); // choosing to initialize as arraylist here, keep general w List everywhere else..idk do we even need biomes
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }
}
