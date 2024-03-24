package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public class GenericResponse extends Response{

    public GenericResponse(int cost, JSONObject extras, String status){
        this.cost = cost;
        this.extras = extras;
        this.status = status;
    }

    @Override
    public String getType(){
        return "generic";
    }

}