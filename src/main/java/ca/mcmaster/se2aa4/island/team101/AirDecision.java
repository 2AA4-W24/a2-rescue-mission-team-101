package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;


public class AirDecision extends Decision {
    private Drone drone;   
    //private JSONObject decision = new JSONObject();

    // Command object
    Command command = new Command();
    EchoResponse response = new EchoResponse(); // will be initialized accordidng to type -> using polymorphism
    Compass compass = drone.getCompass();
    private int counter = 0;
    private String lastCommand; 
    private int edge;
    private int distanceToLand;
    private Boolean land = false;
    // use getters from drone to get other relevant info/objects for decision logic
    // should only need emergency detector and battery through drone
    // commands cover everything else i think

    public AirDecision(Drone drone) {
        super(drone);
    }

    @Override
    public String decide() {
        if (counter == 0){
            command.echo(compass.getDirection());
            
        }
        // first action is echo, so response must be echoresponse
        if (counter == 1){
            if (response.getFound().equals("GROUND")){
                distanceToLand = response.getRange();
                flyForward(distanceToLand);
            } else{
                edge = response.getRange();
            }
        } else {
            while (counter < edge){
                // just for the mvp, it checks for land and returns home immediately
                // ideally, this is put into a method, but since its just temporary, it'll just be done in the if statement
                // need to implement a drone.goHomeCost() or something to figure out when to return, its being simulated by a simple counter for now
                // decision.put("action", "stop"); 
                if (counter % 3 == 0){
                    command.echo(compass.getRight());
                } else if (counter % 3 == 1){
                    if (response.getFound().equals("GROUND")){
                        command.heading(compass.getRight());
                        distanceToLand = respond.getRange();
                        flyForward(distanceToLand);
                        break;
                    }
                    command.echo(compass.getLeft());
                } else {
                    if (response.getFound().equals("GROUND")){
                        command.heading(compass.getLeft());
                        distanceToLand = respond.getRange();
                        flyForward(distanceToLand);
                        break;
                    }
                    command.fly();
                }
                counter++;
            } 
        }
        return command.toString();
    }

    private void flyForward(int distanceToLand){

        int flyCounter = 0;
        while (flyCounter < distanceToLand){
            command.fly();
            flyCounter++;
        }
    }
}