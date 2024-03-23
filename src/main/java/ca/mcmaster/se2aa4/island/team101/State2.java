// YOU JUST ECHOED <-- SECOND STATE

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class State2 extends State{

    private String found;
    private int range;
    private EchoResponse latestEcho;

    private final Logger logger = LogManager.getLogger();


    public State2(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
            found = latestEcho.getFound();
            range = latestEcho.getRange();
    }

    @Override
    public String getNextMove(){
        logger.info("****************IN STATE 2 GETNEXTMOVE******************");


        if(found.equals("GROUND")){
            context.distanceToLand = range;
            context.facingLand = true;

            if(context.eta < context.distanceToLand){

                command.fly();
                context.eta++;
                return command.toString();
                
            }else if (context.eta == context.distanceToLand){

                command.scan();
                context.atLand = true;
                return command.toString();

            }else{ 
                command.stop(); 
                return command.toString();
            }

        }else{ 
            context.edge = range; 
            command.hold();
        }

        return command.toString();
    }

    @Override
    public String getNextState(){
        if(found.equals("GROUND")){
            return "state2";
        }else{ 
            return "state3";
        }
    }
    
}