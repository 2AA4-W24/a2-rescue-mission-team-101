package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DroneController {

    private DroneContext context;
    private Drone drone;
    private State state1, state2, state3, state4, state5, state6;
    private final Logger logger = LogManager.getLogger();

    public DroneController(Drone drone) {
        state1 = new State1(drone, context);
        this.drone = drone;
        context = new DroneContext();
        context.setState(state1);
    }

    public String getNextMove() {
        String nextMove = context.getNextMove();
        logger.info("123pfwf3241h" + nextMove);
        return nextMove;

    }

    
    public void transition() {
        String nextString = context.getNextState();
        State next = null;
        logger.info(nextString + " NEXT STRING");
        switch(nextString){
            case "State1":
                next = state1;
                break;

            case "StateFirstEchoAnalyzer":
                state2 = new StateFirstEchoAnalyzer(drone, context);
                next = state2;
                break;
            case "StateFlyForward":
                state3 = new StateFlyForward(drone, context);
                next = state3;
                break;

            case "StateFindIslandFly":
                state4 = new StateFindIslandFly(drone, context);

                next = state4;
                break;

            case "StateFindIslandEcho":
                state5 = new StateFindIslandEcho(drone, context);
                next = state5;
                break;
            default: 
                break;
        }

        if(next != null){
            context.setState(next);
        }
    }
}
