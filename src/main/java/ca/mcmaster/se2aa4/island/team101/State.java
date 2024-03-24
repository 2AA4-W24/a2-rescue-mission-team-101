package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class State{
    private final Logger logger = LogManager.getLogger();

    protected Command command;
    protected Compass compass;
    protected Response latestResponse;
    protected DroneContext context;

    public State(Drone drone, DroneContext context){
        this.command = new Command();
        this.compass = drone.getCompass();
        this.latestResponse = drone.getLatestResponse();
        this.context = context;
        logger.info(this.latestResponse); 
    }

    public abstract Command getNextMove();

    public abstract String getNextState();

}