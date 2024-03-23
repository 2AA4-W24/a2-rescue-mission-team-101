package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public class GenericResponse extends Response{
    private int cost;
    private JSONObject extras;
    private String status;

    public GenericResponse(int cost, JSONObject extras, String status){
        this.cost = cost;
        this.extras = extras;
        this.status = status;
    }

    @Override
    public int getCost(){
        return this.cost;
    }

    @Override
    public String getStatus(){
        return this.status;
    }

    @Override
    public JSONObject getExtras(){
        return this.extras;
    }

    @Override
    public String getType(){
        return "generic";
    }

}