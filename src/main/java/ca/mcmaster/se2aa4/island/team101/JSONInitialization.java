package ca.mcmaster.se2aa4.island.team101;
import java.io.StringReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONInitialization implements Initialization<JSONObject> {
    private JSONObject info;

    public JSONInitialization(String data){
        this.info = new JSONObject(new JSONTokener(new StringReader(data)));
    }

    @Override
    public String toString(){
        if (info != null) {return "Initialization info:\n" + info.toString(2);} 
        else {return "No initialization info.";}
    }

    @Override
    public JSONObject getObject(){
        return info;
    }

    @Override
    public String getDirection(){
        return info.getString("heading");
    }

    @Override
    public Integer getBatteryLevel(){
        return info.getInt("budget");
    }

}
