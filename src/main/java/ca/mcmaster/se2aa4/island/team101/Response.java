package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public abstract class Response{

    public abstract int getCost();
    public abstract JSONObject getExtras();
    public abstract String getStatus();
    @Override
    public String toString(){
        return "Cost = "+this.getCost() + "Extras = " + this.getExtras() + "Status = " + this.getStatus();
    }

    public String getType(){
        return "echo";
    }

}