// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateFindIslandEcho extends State{
    
    private final Logger logger = LogManager.getLogger();
    private EchoResponse latestEcho;
    private String found;
    private int range;

    public StateFindIslandEcho(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        found = latestEcho.getFound();
        range = latestEcho.getRange();
        
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();

        if (context.left){
            logger.info("********************** LAST ECHO WAS LEFT ECHO");
            if (found.equals("GROUND")){ // this will always be false for the first echo
                compass.turnLeft();
                command.heading(compass.getDirection());
            } else {
                command.fly();
                context.distanceToEdge--;
            }
            return command;
        }

        
        if (!context.left){
            logger.info("********************** LAST ECHO WAS RIGHT ECHO");
            if (found.equals("GROUND")){
                compass.turnRight();
                command.heading(compass.getDirection());
            } else {
                command.fly();
                context.distanceToEdge--;
            }
            return command;
        }
    
        return command;

    } 

    @Override
    public String getNextState(){
        if (found.equals("GROUND")){
            context.distanceToLand = range;
            return "StateFlyForward";
        } else {
            context.edge = range;
            logger.info("TRANSITION INTO FLY");
            return "StateFindIslandFly";
        }
    }

}