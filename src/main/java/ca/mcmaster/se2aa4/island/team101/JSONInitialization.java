package ca.mcmaster.se2aa4.island.team101;
import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONInitialization implements Initialization<JSONObject> {
    
    private final Logger logger = LogManager.getLogger();

    private JSONObject info;
    private String direction;
    private Integer batteryLevel;

    public JSONInitialization(){}

    @Override
    public String toString(){
        if (info != null) {return "Initialization info:\n" + info.toString(2);} 
        else {return "No initialization info.";}
    }

    @Override 
    public void initialize(String data){
        this.info = new JSONObject(new JSONTokener(new StringReader(data)));
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", getDirection());
        logger.info("Battery level is {}", getBatteryLevel());
    }

    @Override
    public JSONObject getObject(){
        return info;
    }

    public String getDirection(){
        return direction;
    }

    public Integer getBatteryLevel(){
        return batteryLevel;
    }

}
