package ca.mcmaster.se2aa4.island.team101;
import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Initializer {
    private String rawData;
    private Integer budget;
    private AreaMap map;
    private Compass compass;

    private final Logger logger = LogManager.getLogger();

    public Initializer(String data){
        this.rawData = data;
    }

    private void initialize(String rawData){
        logger.info("****************IN INITIALIZER INITIALIZE******************");
        JSONObject rawJSON = new JSONObject(new JSONTokener(new StringReader(rawData)));
        logger.info("****************made raw json");
        compass = new Compass(rawJSON.optString("heading"));
        budget = rawJSON.optInt("budget");
        map = new AreaMap();
        logger.info("Assigned compass, budget, map");
        logger.info("****************COMPASS -> " + compass.getDirection());
        logger.info("****************BUDGET -> " + budget);
    }

    public String toString(){
        if (rawData != null) {return "Initialization info:\n" + rawData.toString();} 
        else {return "No initialization info.";}
    }

    public Drone assembleDrone(){
        logger.info("****************IN INITIALIZER ASSEMBLEDRONE******************");
        initialize(rawData);
        logger.info("Finished initializing stuff and back in assembledrone method now, returning the drone");
        return new Drone(budget, map, compass);
    }

}
