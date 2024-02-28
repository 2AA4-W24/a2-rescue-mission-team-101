package ca.mcmaster.se2aa4.island.team101;

import java.io.StringReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONResponse implements Response<JSONObject>{
    private JSONObject response;

    public JSONResponse(String data){
        this.response = new JSONObject(new JSONTokener(new StringReader(data)));
    }

    @Override
    public String toString(){
        return "** Response received:\n"+response.toString(2);
    }

    @Override
    public JSONObject getExtraInfo(){
        return response.getJSONObject("extras");
    }

    public Integer getCost(){
        return response.getInt("cost");
    }
}
