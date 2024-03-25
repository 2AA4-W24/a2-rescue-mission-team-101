package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;

import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Command implements CommandReceiver{

    private JSONObject command = new JSONObject();
    private JSONObject parameters = new JSONObject();
    
    // empty constructor
    public Command(){}

    // Ex. { "action": "fly" }
    @Override
    public void fly(){
        command.put("action", FLY);
    }

    // Ex. { "action": "stop" }
    @Override
    public void stop(){
        command.put("action", STOP);
    }

    // Ex. { "action": "heading", "parameters": { "direction": "N" } }
    @Override
    public void heading(String direction){
        parameters.put("direction", direction);
        command.put("action", HEADING);
        command.put("parameters", parameters);
    }

    // Ex. { "action": "echo", "parameters": { "direction": "E" } }
    @Override
    public void echo(String direction){
        parameters.put("direction", direction);
        command.put("action", ECHO);
        command.put("parameters", parameters);
    }

    // Ex. { "action": "scan" }
    @Override
    public void scan(){
        command.put("action", SCAN);
    }

    @Override
    public String getType() {
        return command.optString("action", null);
    }

    @Override
    public String toString(){
        return command.toString();
    }

}