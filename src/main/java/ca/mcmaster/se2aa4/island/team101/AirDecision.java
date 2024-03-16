package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Command command;
    private GenericResponse response;
    private Compass compass;
    private int counter = 0;
    private int edge;
    private int distanceToLand;

    public AirDecision(Drone drone, GenericResponse response) {
        super(drone);
        this.command = new Command();
        this.compass = drone.getCompass();
        this.response = response;
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

    private void flyForward(int distanceToLand) {
        for (int i = 0; i < distanceToLand; i++) {
            command.fly();
        }
    }

    public String decideLand() {
        command.scan();
        return command.toString();
    }

    public String getType(){
        return command.getType();
    }
}
