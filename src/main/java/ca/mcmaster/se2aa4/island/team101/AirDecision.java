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
                        distanceToLand = response.getRange();
                        flyForward(distanceToLand);
                        break;
                    }
                    command.echo(compass.getLeft());
                } else {
                    if (response.getFound().equals("GROUND")){
                        command.heading(compass.getLeft());
                        distanceToLand = response.getRange();
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

    // needs to be refactored but just putting it here
    // logic for once you are on the island looking for a creek
    public String decideLand(){
        // scan, forward 3
        // scan again
        // if the scan turns up 
        // set the point to where u scanned cuz distance calculations are all relative to each other

        // NOOOO one tile YEAH! WOO! YEAH! 
            // it'll store one tile then like skip over the others, the tile it stores will be the center of the 3x3
            // and the rest will be blank
        // can we assume there's only ONE creek per 3x3 grid? oh well gonna assume it anyway :3
        

        // if you just scanned
            // fly forward 3 times
        // if you just flew forward three times --- 
            // scan

           // while the echo is giving you ground keep doing this thing
           // if the echo gives u water then eacho left and right, go whichever way is returning land
        /*
             * echo forward;
             * if echo found ground
                * if you just scanned || if you just flew && flycount < 3
                *      fly forward;
                *      flycount++;
                * if you just flew && flycount == 3 ("else")
                *      flycount == 0;
                *      scan;
            * else if echo found water
                * if echo left gives u land, turn and go this way
                * else if echo right gives u land turn and go this way
        */

        // now need a way to make sure it stays on land
        // need to use echo
        // like the beginning, echo, make a count, decrease it as you go
    
        command.scan();



        return command.toString();
    }
}