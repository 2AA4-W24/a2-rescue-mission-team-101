package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;


public class AirDecision extends Decision {
    private Drone drone;   
    //private JSONObject decision = new JSONObject();

    // Command object
    Command command = new Command();
    Response response; // will be initialized accordidng to type -> using polymorphism

    private int counter = 0;
    private String lastCommand; 
    // use getters from drone to get other relevant info/objects for decision logic
    // should only need emergency detector and battery through drone
    // commands cover everything else i think

    public AirDecision(Drone drone) {
        super(drone);
    }

    @Override
    public String decide() {
        if (counter > 20){
            // just for the mvp, it checks for land and returns home immediately
            // ideally, this is put into a method, but since its just temporary, it'll just be done in the if statement
            // need to implement a drone.goHomeCost() or something to figure out when to return, its being simulated by a simple counter for now
            // decision.put("action", "stop"); 
            command.stop();
        } else{
            if (counter % 2 == 0){
                //JSONObject parameters = new JSONObject();
                // temporarily set direction as E, need to determine the correct echo direction later
                //parameters.put("direction", "E");
                //decision.put("action", ECHO);
                //decision.put("parameters", parameters);
                command.echo(EAST); 
                lastCommand = "echo";
            }
            else {
                lastCommand = "fly";
                command.fly();
            }
        }
        counter += 1;
        // add a switch case for the command object and depending on what its holding grab a response
        return command.toString();
    }

    private void flyForward(int distanceToLand){

        int flyCounter = 0;
        while (flyCounter < distanceToLand){
            command.fly();
        }
    }
}

