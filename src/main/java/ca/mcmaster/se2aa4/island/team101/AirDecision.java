package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;


public class AirDecision extends Decision {
    private Drone drone;   
    //private JSONObject decision = new JSONObject();

    // Command object
    Command command = new Command();
    Response response; // will be initialized accordidng to type -> using polymorphism

    private int counter = 0;
    // use getters from drone to get other relevant info/objects for decision logic
    // should only need emergency detector and battery through drone
    // commands cover everything else i think

    public AirDecision(Drone drone) {
        super(drone);
    }

    @Override
    public String decide() {
        if (counter % 2 == 0){
            drone.setNextMove("echo");
            command.echo(EAST);
            // if (response.getFound() == "OUT_OF_RANGE"){
                // needs a way to turn around
                command.stop();
            // }
        } else{
            drone.setNextMove("fly");
            command.fly();
        }
        counter += 1;
        // add a switch case for the command object and depending on what its holding grab a response
        return command.toString();
    }
}

