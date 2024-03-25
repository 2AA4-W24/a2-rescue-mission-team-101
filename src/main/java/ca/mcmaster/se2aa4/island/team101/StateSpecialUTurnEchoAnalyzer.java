// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateSpecialUTurnEchoAnalyzer extends State{
    
    private final Logger logger = LogManager.getLogger();
    private EchoResponse latestEcho;
    private int range;

    public StateSpecialUTurnEchoAnalyzer(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        range = latestEcho.getRange();
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        command.fly();
        context.distanceToLand = range;
        return command;
    } 

    @Override
    public String getNextState(){
        return "StateFlyForward";
    }


}