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

    public List<String> getCreeks() {
        return toList(extras.getJSONArray("creeks"));
    }

    public List<String> getSites() {
        return toList(extras.getJSONArray("sites"));
    }

    private List<String> toList(JSONArray arr) {
        List<String> list = new ArrayList<>(); // choosing to initialize as arraylist here, keep general w List everywhere else
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }
}
