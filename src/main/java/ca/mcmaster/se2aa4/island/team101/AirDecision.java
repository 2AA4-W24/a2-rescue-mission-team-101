package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirDecision extends Decision {
    private final Logger logger = LogManager.getLogger();

    private Command command;
    private GenericResponse response;
    private Compass compass;
    private int counter = 0, distanceToEdge = 0, eta = 0, stage = 0, scanCount=0, turn=0;
    private int edge=0;
    private int distanceToLand;
    Boolean facingLand=false, atLand=false, scanComplete=false, lastTurnRight = false, activateUTurn = false;
    String newDirection;
    AreaMap map;

    public AirDecision(Drone drone) {
        super(drone);
        this.compass = drone.getCompass();
        //this.map = drone.getMap();
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
        // TURN RIGHT WHEN LAND IS HIT
        if (scanCount == 0){
            logger.info("TURNING RIGHT ONCE ON THE ISLAND*******");
            newDirection = compass.getRight();
            command.heading(newDirection);
            compass.updateHeading(newDirection);
            lastTurnRight = true;
            scanCount++;
            return command.toString();
        }
        else{
            // scan until ocean is FOUND
            if (scanCount % 2 == 0){

                if (response.hasOnlyOcean()){
                    // U TURN METHOD HERE (REMEMBER WHAT LAST TURN WAS i.e. if last turn was to the right, next turn should be to the left)
                    activateUTurn = true;
                }
                if (activateUTurn){
                    return uTurn();
                }

                command.fly();
                scanCount++;
                return command.toString();
            }
            else{
                logger.info("SCANNING THE ISLAND BUZZ BUZZ *****");
                command.scan();
                scanCount++;
                return command.toString();
            }
        }

        //return command.toString();
    }

    public String uTurn(){

        if (lastTurnRight){

            // 1/2 turn
            if (turn==0){
                command.heading(compass.getLeft());
                compass.updateHeading(compass.getLeft());
                turn=1;
                return command.toString();
            }
            else if(turn==1){
                command.heading(compass.getLeft());
                compass.updateHeading(compass.getLeft());
                turn=0;
                lastTurnRight = false;
                activateUTurn = false;
                return command.toString();
            }
        }
        else{
            // 1/2 turn
            if (turn==0){
                command.heading(compass.getLeft());
                compass.updateHeading(compass.getLeft());
                turn=1;
                return command.toString();
            }
            else if (turn==1){
                command.heading(compass.getLeft());
                compass.updateHeading(compass.getLeft());
                turn=0;
                lastTurnRight = true;
                activateUTurn = false;
                return command.toString();
            }
        }
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
