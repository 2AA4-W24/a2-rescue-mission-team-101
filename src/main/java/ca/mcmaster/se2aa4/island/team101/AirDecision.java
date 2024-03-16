package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Command command;
    private GenericResponse response;
    private Compass compass;
    private int counter = 0, ind = 0;
    private int edge;
    private int distanceToLand;

    public AirDecision(Drone drone) {
        super(drone);
        this.command = new Command();
        this.compass = drone.getCompass();
        //this.response = response;
    }
    
    public void updateResponse(GenericResponse newResponse){
        response = newResponse;
    }

    @Override
    public String decide() {
        logger.info(counter);
        logger.info("****************************** BASE CASE");
        if (counter == 0){
            command.echo(compass.getDirection());
            counter+=1;
            logger.info("***************************** FIRST ECHO");
            logger.info(counter);

        }
        // first action is echo, so response must be echoresponse
        else if (counter == 1){
            if (((EchoResponse)response).getFound().equals("GROUND")){
                distanceToLand = ((EchoResponse)response).getRange();
                flyForward(distanceToLand);
                counter++;
            } else{
                logger.info("******************** GROUND IS NOT AHEAD OF US, FINDING EDGE");
                edge = ((EchoResponse)response).getRange();
                logger.info(counter);
                counter++;
            }

        } else {
            if (ind < edge){
                // just for the mvp, it checks for land and returns home immediately
                // ideally, this is put into a method, but since its just temporary, it'll just be done in the if statement
                // need to implement a drone.goHomeCost() or something to figure out when to return, its being simulated by a simple counter for now
                // decision.put("action", "stop"); 
                if (ind % 3 == 0){
                    command.echo(compass.getRight());
                } else if (ind % 3 == 1){
                    if (((EchoResponse)response).getFound().equals("GROUND")){
                        command.heading(compass.getRight());
                        distanceToLand = ((EchoResponse)response).getRange();
                        flyForward(distanceToLand);
                    }
                    command.echo(compass.getLeft());
                } else {
                    if (((EchoResponse)response).getFound().equals("GROUND")){
                        command.heading(compass.getLeft()); // TURNING NEEDS ITS SEPARATE COMMAND< HALF OF THIS CANNOT BE DONE AT ONCE
                        distanceToLand = ((EchoResponse)response).getRange();
                        flyForward(distanceToLand);
                    }
                    command.fly();
                }
                ind+=1;
            }
            if (ind == edge){
                command.scan();
                ind++;
            }
            else if (ind > edge){
                command.stop();
            }
            counter++;
        }
        return command.toString();
    }

    private void flyForward(int distanceToLand) {
        for (int i = 0; i < distanceToLand; i++) {
            command.fly();
        }
    }

    public String decideLand() {
        command.scan();
        return command.toString();
    }

    @Override
    public String getType(){
        return command.getType();
    }
}
