package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONObject;

import static ca.mcmaster.se2aa4.island.team101.CommandStrings.*;

public class Command {
    private JSONObject command = new JSONObject(); // where we .put() to
    private JSONObject parameters = new JSONObject(); // for passing parameters into command

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

    // to use in Decision logic
    public String toString(){
        return command.toString();
    }

    public String getType() {
        return command.optString("action", null);
    }


    // add a method to push off the top command from the queue.
    // then this will make changed = true and notify observers.
    // so then drone knows the command that was just pushed and it can update itself accordingly
    // make a getupdate or smth that takes an observer and returns the data from the command , the command itself ig
    // and then the sendcommand(command) will just pop off the stack to return the command so u can get it in explorer
    // and it will say it sent the command and it will set this.command to the command and this.changed to true and will notifyobservers
}
