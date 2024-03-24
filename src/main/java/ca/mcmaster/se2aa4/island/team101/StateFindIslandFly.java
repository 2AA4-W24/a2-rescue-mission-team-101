// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateFindIslandFly extends State{
    
    private final Logger logger = LogManager.getLogger();
    private EchoResponse latestEcho;
    private String found;
    private int range;

    public StateFindIslandFly(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        found = latestEcho.getFound();
        range = latestEcho.getRange();
        logger.info(context.left + " CONTEXT LEFT");

    }

    @Override
    public String getNextMove(){
        this.command = new Command();
        logger.info(context.left + " CONTEXT LEFT");

        if (context.left){
            logger.info("********************** LAST ECHO WAS LEFT ECHO");
            context.left = false;
            command.echo(compass.getRight());
            return command.toString();
        }

        
        else if (!context.left){
            logger.info("********************** LAST ECHO WAS RIGHT ECHO");

            context.left = true;
            command.echo(compass.getLeft());
            logger.info(command.toString() + "COMMAND");

            return command.toString();
        }

    
        return command.toString();

    } 

    @Override
    public String getNextState(){
        return "StateFindIslandEcho";
    }

}