package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DroneController implements Controller{

    private DroneContext context;
    private Drone drone;
    private State state1, state2, state3, state4, state5, state6, state7, state8, state9, state10, state11, state12;
    private final Logger logger = LogManager.getLogger();

    public DroneController(Drone drone) {
        state1 = new State1(drone, context);
        this.drone = drone;
        context = new DroneContext();
        context.setState(state1);
    }

    @Override
    public Command getNextMove() {
        Command nextMove = context.getNextMove();
        return nextMove;

    }

    @Override
    public void transition() {
        String nextString = context.getNextState();
        State next = null;
        logger.info("NEXT STATE : " + nextString);
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
            case "StateCreekSearchFly":
                state6 = new StateCreekSearchFly(drone, context);
                next = state6;
                break;
            case "StateCreekSearchScan":
                state7 = new StateCreekSearchScan(drone, context);
                next = state7;
                break;
            case "StateUTurnEcho1":
                state8 = new StateUTurnEcho1(drone, context);
                next = state8;
                break;
            case "StateUTurnEcho2":
                state9 = new StateUTurnEcho2(drone, context);
                next = state9;
                break;
            case "StateUTurnEchoAnalyzer1":
                state10 = new StateUTurnEchoAnalyzer1(drone, context);
                next = state10;
                break;
            case "StateUTurnEchoAnalyzer2":
                state11 = new StateUTurnEchoAnalyzer2(drone, context);
                next = state11;
                break;
            case "StateExecuteUTurn":
                state12 = new StateExecuteUTurn(drone, context);
                next = state12;
                break;
            default: 
                break;
        }

        if(next != null){
            context.setState(next);
        }
    }
}
