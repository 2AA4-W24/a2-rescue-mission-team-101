// YOU JUST SAW NO GROUND, AND GOT THE EDGE <-- LOOK FOR LAND

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class State3 extends State{

    private final Logger logger = LogManager.getLogger();


    public State3(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public String getNextMove(){

        logger.info("****************IN STATE 3 GETNEXTMOVE******************");


        if(context.distanceToEdge < context.edge){
            command.fly();
            context.eta++;
            return command.toString();

        }else if(context.facingLand){
            context.atLand = true;
        }

        if(context.atLand && !context.scanComplete){
            command.scan();
            context.scanComplete = true;
            return command.toString();
        }

        if(context.scanComplete){
            command.hold();
        }

        return command.toString();
    }

    @Override
    public String getNextState(){
        if(context.scanComplete){
            return "state 4";
        }else{
            return "state 5";
        }
    }

    
}