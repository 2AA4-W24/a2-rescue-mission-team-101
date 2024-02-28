package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;

public class Command {
    private static final String FLY = "fly";
    private static final String STOP = "stop";
    private static final String HEADING = "heading";
    private static final String ECHO = "echo";
    private static final String SCAN = "scan";

    private JSONObject command = new JSONObject(); // where we .put() to
    private JSONObject parameters = new JSONObject(); // for passing parameters into command

    public Command(){}

    // Ex. { "action": "fly" }
    public void fly(){
        command.put("action", "fly");
    }

    // Ex. { "action": "stop" }
    public void stop(){
        command.put("action", "stop");
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
        command.put("action", "scan");
    }

    // to use in Decision logic
    public String toString(){
        return command.toString();
    }

}
