// ARRIVED AT LAND, LOOKING FOR CREEKS <-- last response will have been a scan

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class State4 extends State{

    private ScanResponse latestScan;
    private Boolean allOcean;

    private final Logger logger = LogManager.getLogger();


    public State4(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestScan = (ScanResponse) latestResponse;
            allOcean = latestScan.isAllOcean();
    }

    @Override
    public String getNextMove(){

        logger.info("****************IN STATE 4 GETNEXTMOVE******************");

        if(context.scanCount >= 1200){
            command.stop();
            return command.toString();
        }

        else if(context.scanCount%2==1){
            // not null??? why/?????? 
            if(!context.activateUTurn && allOcean !=null && allOcean){
                context.activateUTurn = true;
            }
            if(context.activateUTurn){
                command.hold();
            }

            command.fly();
            context.scanCount++;
            return command.toString();

        }else{
            command.scan();
            context.scanCount++;
            
        }
        return command.toString();
    }

    @Override
    public String getNextState(){
        if(context.activateUTurn){
            return "state6";
        }else{
            return "state1";
        }
    }
    
}