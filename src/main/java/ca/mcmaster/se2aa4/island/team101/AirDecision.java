package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Command command;
    private GenericResponse response;
    private Compass compass;
    private int counter = 0, distanceToEdge = 0, eta = 0, stage = 0, counter2=0;;
    private int edge=0;
    private int distanceToLand;
    Boolean facingLand=false, atLand=false, scanComplete=false;
    String newDirection;
    AreaMap map;

    public AirDecision(Drone drone) {
        super(drone);
        this.compass = drone.getCompass();
        this.map = drone.getMap();
        //this.response = response;
    }
    
    public void updateResponse(GenericResponse newResponse){
        response = newResponse;
    }

    @Override
    public String decide() {
        command = new Command();
        logger.info(compass.getDirection());
        logger.info(counter);
        logger.info("****************************** BASE CASE");
        if (counter == 0){
            // echo forwards
            command.echo(compass.getDirection());
            counter+=1;
            logger.info("***************************** FIRST ECHO");
            logger.info(counter);
            return command.toString();
        }
        // first action is echo, so response must be echoresponse
        else if (counter == 1){
            logger.info("*******************************");
            if (((EchoResponse)response).getFound().equals("GROUND")){
                distanceToLand = ((EchoResponse)response).getRange();
                facingLand = true;
                if (eta < distanceToLand){ // if the ground is infront of us and we are not there yet, fly forwards
                    command.fly();
                    eta++;
                    return command.toString();
                }
                else if (eta == distanceToLand){ // if we are at the island, scan
                    command.scan();
                    atLand = true;
                    return command.toString();
                }
                else { // stop the drone after scanning for now
                    command.stop();
                    return command.toString();
                }

            } else{
                logger.info("******************** GROUND IS NOT AHEAD OF US, FINDING EDGE");
                edge = ((EchoResponse)response).getRange();
                counter++;
            }
        }

        if (distanceToEdge < edge){
            if (facingLand && eta < distanceToLand){ // if facing the land and not yet at land, then fly forward
                command.fly();
                eta++;
                return command.toString();
            } else if (facingLand){
                atLand = true;
            }
            if (atLand && !scanComplete){ // SCAN IF AT LAND
                command.scan();
                scanComplete = true;
                return command.toString();
            }
            if (scanComplete){ // STOP IF SCANNED
                // command.stop();
                //return command.toString();
                return creekSearch();
            }

            // FOUR STAGES WHEN SEARCHING
            //      ECHO RIGHT -> FLY OR CHANGE DIRECTION -> ECHO LEFT -> FLY OR CHANGE DIRECTION
            //      STAGE 1              STAGE 2              STAGE 3            STAGE 4

            if (stage % 4 == 0){ // ECHO RIGHT
                stage++;
                logger.info("********************** STAGE 1");
                command.echo(compass.getRight());
                return command.toString();
            } 
            else if (stage % 4 == 1){ // ANALYZE ECHO RIGHT
                stage++;
                logger.info("********************** STAGE 2");
                logger.info(((EchoResponse)response).getFound());

                if (((EchoResponse)response).getFound().equals("GROUND")){ // IF GROUND IS FOUND, CHANGE HEADING AND FLY TOWARDS IT
                    newDirection = compass.getRight(); // MAKE SEPARATE LIKE .TURN that deals with reassigning direction (probably in command)
                    compass.updateHeading(newDirection);
                    command.heading(newDirection);
                    distanceToLand = ((EchoResponse)response).getRange();
                    facingLand = true;
                    return command.toString();
                }
                logger.info(compass.getDirection() + "******************** FLYING");
                command.fly();
                distanceToEdge+=1;
                return command.toString();
            } 
            else if (stage % 4 == 2){
                logger.info("********************** STAGE 3");
                stage++;
                command.echo(compass.getLeft());
                return command.toString();
            }
            else{
                logger.info("********************** STAGE 4");
                stage++;
                if (((EchoResponse)response).getFound().equals("GROUND")){ // IF GROUND IS FOUND, CHANGE HEADING AND FLY TOWARDS IT
                    newDirection = compass.getLeft();
                    compass.updateHeading(newDirection);
                    command.heading(newDirection);
                    distanceToLand = ((EchoResponse)response).getRange();
                    facingLand = true;
                    return command.toString();
                }
                command.fly();
                distanceToEdge+=1;
                return command.toString();
            }
            
        }
        
        return command.toString();
    }

    private String creekSearch(){
        logger.info("************************IN CREEKSEARCH");
        command = new Command();
        
        // if the current tile is not null then stop
        if(!(map.getTile(compass.getPosition()).isEmpty())){
            logger.info("***CURRENT TILE IS NOT EMPTY SO STOPPING***");
            logger.info("**CURRENT TILE CREEK ID: " + map.getTile(compass.getPosition()).getCreekID());
            logger.info("**CURRENT TILE SITE ID: " + map.getTile(compass.getPosition()).getSiteID());
            command.stop();
            return command.toString();
        }

        logger.info("***CURRENT TILE IS EMPTY CONTINUE***");

        // logger.info(compass.getDirection());
        // logger.info(counter);

        // SATE 0: 
        // ECHO TO SEE IF YOU CAN FLY FORWARDS
        if (counter2==0){
            logger.info("************************* STATE 0");
            logger.info("**COUNTER2 = " + counter2);
            // should add something to scan if ur current tile is over land idk
            // so you dont miss the first tile after land finding phase bc rn itll just immediately echo then
            // go fwd if it wont be sending itself into the ocean

            // echo forwards
            command.echo(compass.getDirection());
            counter2=1;
            return command.toString();
        }
        // STATE 1:
        // FLY FORWARDS, IF YOU CAN
        // OTHERWISE ECHO LEFT
        else if (counter2==1){
            logger.info("************************* STATE 1");
            logger.info("**COUNTER2 = " + counter2);
            if (((EchoResponse)response).getFound().equals("GROUND")){
                // if u see ground fly forwards
                command.fly();
                counter2=3; // straight to scanning state
            }else if (((EchoResponse)response).getFound().equals("OCEAN")){
                // otherwise check left
                command.echo(compass.getLeft());
                counter2=2;
            }
            
            return command.toString();
        }
        // STATE 2:
        // YOU JUST ECHOED LEFT
        else if (counter2==2){
            logger.info("************************* STATE 2");
            logger.info("**COUNTER2 = " + counter2);
            if (((EchoResponse)response).getFound().equals("GROUND")){
                // If you saw ground turn left
                command.heading(compass.getLeft());
                // bc of the weird turns idk if this will send it off land or not but it should be minimal error not a big issue rn
            }else if (((EchoResponse)response).getFound().equals("OCEAN")){
                // if you didn't see land you need to go right it's the only other option
                command.heading(compass.getRight());
            }
            counter2=3;
            return command.toString();
        }
        // STATE 3:
        // SCANNING
        else if (counter2==3){
            logger.info("************************* STATE 3");
            logger.info("**COUNTER2 = " + counter2);
            command.scan();
            // map should auto update bc of the code in drone update for whenever it recieves a scan response
            counter2=0; // back to the state of looking in front of you
            return command.toString();
        }

        // need a way to stop it maybe a state 4 once all the tiles are scanned but idk how to tell if they are all scanned

        // it just yells at me if i don't have this ugh idk
        return command.toString();
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
