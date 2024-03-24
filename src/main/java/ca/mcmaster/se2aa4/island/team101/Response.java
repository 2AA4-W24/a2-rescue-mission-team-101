package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public abstract class Response{

    protected int cost;
    protected JSONObject extras;
    protected String status;

    public int getCost(){
        return cost;
    }

    public JSONObject getExtras(){
        return extras;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return "Cost = "+this.getCost() + "Extras = " + this.getExtras() + "Status = " + this.getStatus();
    }

    public String getType(){
        return "echo";
    }

}