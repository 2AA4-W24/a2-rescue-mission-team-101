package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Command command;
    private ResponseHandler<?> responseHandler;
    private Compass compass;
    private int counter = 0;
    private int edge;
    private int distanceToLand;

    public AirDecision(Drone drone, ResponseHandler<?> responseHandler) {
        super(drone);
        this.command = new Command(); // command is like a storage item, after each move kill it and make a clean one 
        this.responseHandler = responseHandler;
        this.compass = drone.getCompass();
    }

    @Override
    public String decide() {
        logger.info("Counter: " + counter);

        switch (counter) {
            case 0:
                command.echo(compass.getDirection());
                counter++;
                break;
            case 1:
                EchoResponse echoResponse = (EchoResponse) responseHandler.handle(null, null);
                if (echoResponse.getFound().equals("GROUND")) {
                    distanceToLand = echoResponse.getRange();
                    flyForward(distanceToLand);
                    counter++;
                } else {
                    edge = echoResponse.getRange();
                }
                break;
            default:
                counter = 0;
                while (counter < edge) {
                    if (counter % 3 == 0) {
                        command.echo(compass.getRight());
                    } else if (counter % 3 == 1) {
                        EchoResponse echoResponseRight = (EchoResponse) responseHandler.handle(null, null);
                        if (echoResponseRight.getFound().equals("GROUND")) {
                            command.heading(compass.getRight());
                            distanceToLand = echoResponseRight.getRange();
                            flyForward(distanceToLand);
                            break;
                        }
                        command.echo(compass.getLeft());
                    } else {
                        EchoResponse echoResponseLeft = (EchoResponse) responseHandler.handle(null, null);
                        if (echoResponseLeft.getFound().equals("GROUND")) {
                            command.heading(compass.getLeft());
                            distanceToLand = echoResponseLeft.getRange();
                            flyForward(distanceToLand);
                            break;
                        }
                        command.fly();
                    }
                    counter++;
                }
                break;
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
}
