package ca.mcmaster.se2aa4.island.team101;

import java.io.StringReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONResponse implements Response<JSONObject>{
    private JSONObject response;
    protected JSONObject extras;

    public JSONResponse(String data){
        this.response = new JSONObject(new JSONTokener(new StringReader(data)));
        this.extras = response.getJSONObject("extras"); // Initialize the extras field
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
