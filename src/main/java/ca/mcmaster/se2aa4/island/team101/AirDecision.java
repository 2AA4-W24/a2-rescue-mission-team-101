package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;


public class AirDecision extends Decision {
    private Drone drone;   
    private JSONObject decision = new JSONObject();
    private int counter = 0;


    // use getters from drone to get other relevant info/objects for decision logic
    // should only need emergency detector and battery through drone
    // commands cover everything else i think

    public AirDecision(Drone drone) {
        super(drone);
    }

    @Override
    public String decide() {
        // stuff like this to get ur relevant info
        // Logic specific to drone, use all the info from the drone objet here
        // not sure how to return strings for turning...maybe need to change heading logic to work
        // with however you turn in this, if there's a command to turn n/e/s/w, instead of DIY turning
        // then those commands could be an option to return from here + add to String list in decision
        if (counter > 50){
            decision.put("action", "stop"); 
        } else{
            if (counter % 2 == 0){
                JSONObject parameters = new JSONObject();
                // temporarily set direction as E, need to determine the correct echo direction later
                parameters.put("direction", "E");
                decision.put("action", "echo");
                decision.put("parameters", parameters);
            }
            else {
                decision.put("action", "fly"); 
            }
        }
        counter += 1;
        return decision.toString();
    }
}

