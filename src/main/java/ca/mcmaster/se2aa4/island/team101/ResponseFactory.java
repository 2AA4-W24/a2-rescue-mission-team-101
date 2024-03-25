package ca.mcmaster.se2aa4.island.team101;


import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;

public class ResponseFactory{
    private int cost;
    private String status;
    private JSONObject extras;


    
    public Response getResponse(String type, String rawData){
        parse(rawData);
        if(("echo").equalsIgnoreCase(type)) return new EchoResponse(cost, extras, status);
        else if(("scan").equalsIgnoreCase(type)) return new ScanResponse(cost, extras, status);
        return new GenericResponse(cost, extras, status); // Default to generic
    }

    private void parse(String rawData){
        JSONObject rawJSON = new JSONObject(new JSONTokener(new StringReader(rawData)));
        cost = rawJSON.optInt("cost");
        extras = rawJSON.optJSONObject("extras");
        status = rawJSON.optString("status");
    }
}