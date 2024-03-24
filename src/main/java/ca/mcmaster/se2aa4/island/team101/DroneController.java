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

    private String getNextMove() {
        String nextMove = context.getNextMove();
        logger.info("123pfwf3241h" + nextMove);
        if(!nextMove.equals("hold")){  
            logger.info("123123532g23gw43gh" + nextMove);
            // move this transition into drone.update
        }
        return nextMove;

    }



    public String temp(){
        String nextMove = getNextMove();
        transition();
        if(nextMove.equals("hold")){ 
            logger.info("123pfwf3241h" + nextMove);
            nextMove = getNextMove();

        }
        return nextMove;
    }
    
    public void transition() {
        String nextString = context.getNextState();
        logger.info("1231h" + context.getNextState());
        State next = null;

        switch(nextString){
            case "State1":
                next = state1;
            case "StateFirstEchoAnalyzer":
                state2 = new StateFirstEchoAnalyzer(drone, context);
                next = state2;
            case "StateFlyForward":
                state3 = new StateFlyForward(drone, context);
                next = state3;
            case "StateFindIslandFly":
                state4 = new StateFindIslandFly(drone, context);

                next = state4;
            default:
                state5 = new StateFindIslandEcho(drone, context);
                next = state5;
        }

        if(next != null){
            context.setState(next);
        }
    }
}
