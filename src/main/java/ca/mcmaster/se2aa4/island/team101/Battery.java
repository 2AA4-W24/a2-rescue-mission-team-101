package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battery {

    private final Logger logger = LogManager.getLogger();
    private int batteryLevel;
    private JSONResponse response;
    
    public Battery(Integer initialBattery){ 
        setCharge(initialBattery);
    }

    public int getCharge(){
        return batteryLevel;
    }

    public void setCharge(int charge){
        this.batteryLevel -= charge;
        if (batteryLevel <= 0){
            logger.info("*** Drone is dead. Womp Womp.");
            this.batteryLevel = 0;
        }
    }

}   

