package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;

public class ResponseFactory{
    private int cost;
    private String status;
    private JSONObject extras;

    private final Logger logger = LogManager.getLogger();

    
    public Response getResponse(String type, String rawData){
        logger.info("****************IN RESPONSEFACTORY GETRESPONSE******************");
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