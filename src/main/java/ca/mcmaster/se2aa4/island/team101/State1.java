// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class State1 extends State{
    
    private final Logger logger = LogManager.getLogger();

    public State1(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public String getNextMove(){
        logger.info("****************IN STATE 1 GETNEXTMOVE******************");

        command.echo(compass.getDirection());
        return command.toString();
    } 

    @Override
    public String getNextState(){
        return "state2";
    }

}