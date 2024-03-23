package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;

import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Command{

    private JSONObject command = new JSONObject();
    private JSONObject parameters = new JSONObject();

    public Command(){}

    // Ex. { "action": "fly" }
    public void fly(){
        command.put("action", FLY);
    }

    // Ex. { "action": "stop" }
    public void stop(){
        command.put("action", STOP);
    }

    // Ex. { "action": "heading", "parameters": { "direction": "N" } }
    public void heading(String direction){
        parameters.put("direction", direction);
        command.put("action", HEADING);
        command.put("parameters", parameters);
    }

    // Ex. { "action": "echo", "parameters": { "direction": "E" } }
    public void echo(String direction){
        parameters.put("direction", direction);
        command.put("action", ECHO);
        command.put("parameters", parameters);
    }

    // Ex. { "action": "scan" }
    public void scan(){
        command.put("action", SCAN);
    }

    // Not a real command in the hidden stuff, but an internal one to help the FSM
    public void hold(){
        command.put("action", HOLD);
    }

    // to use in Decision logic
    public String toString(){
        return command.toString();
    }

    public String getType() {
        return command.optString("action", null);
    }

}