package ca.mcmaster.se2aa4.island.team101;

public class EchoResponse extends JSONResponse {
    public EchoResponse(String data) {
        super(data);
    }

    public Integer getRange(){
        return extras.getInt("range");
    }

    public String getFound(){
        return extras.getString("found");
    }
}
