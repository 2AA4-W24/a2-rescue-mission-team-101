package ca.mcmaster.se2aa4.island.team101;

import java.io.StringReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONResponse implements Response<JSONObject>{
    private JSONObject response;
    protected JSONObject extras;

    public JSONResponse(String cmdType, String data){
        this.response = new JSONObject(new JSONTokener(new StringReader(data)));
        this.extras = response.getJSONObject("extras"); // Initialize the extras field
        // so that we know what type of response we're have
        response.put("action", cmdType);
    }

    @Override
    public String toString(){
        return "** Response received:\n"+response.toString(2);
    }

    @Override
    public Integer getCost(){
        return response.getInt("cost");
    }
}

// get rid of all the different types, we use one big case to extract the proper info, scine all responses have the same number of fields.
