package ca.mcmaster.se2aa4.island.team101;
import org.json.JSONObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Drone drone;   
    //private JSONObject decision = new JSONObject();

    // Command object
    Command command;
    JSONResponse response;
    Compass compass;
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
        this.command = new Command();
        this.response = drone.getResponse(); // will be initialized accordidng to type -> using polymorphism
        this.compass = drone.getCompass();
    }

    @Override
    public String decide() {
        logger.info(counter);
        logger.info("aduhwaiuhwaiuhdahiuhwaiuidaidauhwaiu");
        if (counter == 0){
            command.echo(compass.getDirection());
            counter+=1;
            logger.info("aduhwaiuhwaiuhdahiuhwaiuidaidauhwaiu");

            logger.info(counter);

        }
        // first action is echo, so response must be echoresponse
        else if (counter == 1){
            if (((EchoResponse)response).getFound().equals("GROUND")){
                distanceToLand = ((EchoResponse)response).getRange();
                flyForward(distanceToLand);
                counter++;
            } else{
                logger.info("aduhwaiuhwaiuhdahiuhwaiuidaidauhwaiu");
                edge = ((EchoResponse)response).getRange();
                logger.info(counter);

            }

        } else {
            counter = 0;
            while (counter < edge){
                // just for the mvp, it checks for land and returns home immediately
                // ideally, this is put into a method, but since its just temporary, it'll just be done in the if statement
                // need to implement a drone.goHomeCost() or something to figure out when to return, its being simulated by a simple counter for now
                // decision.put("action", "stop"); 
                if (counter % 3 == 0){
                    command.echo(compass.getRight());
                } else if (counter % 3 == 1){
                    if (((EchoResponse)response).getFound().equals("GROUND")){
                        command.heading(compass.getRight());
                        distanceToLand = ((EchoResponse)response).getRange();
                        flyForward(distanceToLand);
                        break;
                    }
                    command.echo(compass.getLeft());
                } else {
                    if (((EchoResponse)response).getFound().equals("GROUND")){
                        command.heading(compass.getLeft());
                        distanceToLand = ((EchoResponse)response).getRange();
                        flyForward(distanceToLand);
                        break;
                    }
                    command.fly();
                }
                counter+=1;
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

