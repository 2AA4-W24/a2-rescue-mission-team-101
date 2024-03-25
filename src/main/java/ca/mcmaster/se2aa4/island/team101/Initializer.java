package ca.mcmaster.se2aa4.island.team101;
import java.io.StringReader;


import org.json.JSONObject;
import org.json.JSONTokener;


public class Initializer {
    private static Initializer instance; // Singleton instance
    private String rawData;
    private Integer budget;
    private AreaMap map;
    private Compass compass;


    public Initializer(String data){
        this.rawData = data;
    }

    // Get singleton instance 
    public static Initializer getInstance(String data) {
        if (instance == null) {
            instance = new Initializer(data);
        }
        return instance;
    }

    private void initialize(String rawData){
        JSONObject rawJSON = new JSONObject(new JSONTokener(new StringReader(rawData)));
        compass = new Compass(rawJSON.optString("heading"));
        budget = rawJSON.optInt("budget");
        map = new AreaMap();
    }

    public String toString(){
        if (rawData != null) {return "Initialization info:\n" + rawData.toString();} 
        else {return "No initialization info.";}
    }

    public Drone assembleDrone(){
        initialize(rawData);
        return new Drone(budget, map, compass);
    }

}
