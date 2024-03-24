// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateFlyForward extends State{
    
    private final Logger logger = LogManager.getLogger();

    public StateFlyForward(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public String getNextMove(){
        if (context.distanceToLand <= 0){
            command.stop();
        } else {
            command.fly();
        }
        context.distanceToLand --;
        return command.toString();
    } 

    @Override
    public String getNextState(){
        if (context.distanceToLand <= 0){
            return "StateCreekSearch";
        } else {
            return "StateFlyForward";
        }
    }

}