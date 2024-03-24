// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateUTurnEchoAnalyzer2 extends State{
    
    private final Logger logger = LogManager.getLogger();
    private EchoResponse latestEcho;
    private String found;
    private int range;

    public StateUTurnEchoAnalyzer2(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        found = latestEcho.getFound();
        range = latestEcho.getRange();
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        if (!found.equals("GROUND")){
            command.stop();
            return command;
        }
        command.fly();
        return command;
    } 

    @Override
    public String getNextState(){
        this.command = new Command();

        if (found.equals("GROUND")){
            context.distanceToLand = range;
            return "StateFlyForward";
        } else {
            return "SpecialUTurn";
        }
    }


}