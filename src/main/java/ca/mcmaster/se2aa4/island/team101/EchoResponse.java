package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

public class EchoResponse extends Response{
    private int cost;
    private JSONObject extras;
    private String status;

    // Specific to Echo -> Liskov
    private int range;
    private String found;

    public EchoResponse(int cost, JSONObject extras, String status){
        this.cost = cost;
        this.extras = extras;
        this.status = status;
        this.range = extras.optInt("range");
        this.found = extras.optString("found");
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
        return "echo";
    }

    // Specific to Echo -> Liskov
    public int getRange(){
        return this.range;
    }

    public String getFound(){
        return this.found;
    }

}