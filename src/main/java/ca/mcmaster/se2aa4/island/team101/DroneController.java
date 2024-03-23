package ca.mcmaster.se2aa4.island.team101;

public class DroneController {

    private DroneContext context;
    private State state1, state2, state3, state4, state5, state6;
    
    public DroneController(Drone drone) {
        context = new DroneContext();

        state1 = new State1(drone, context);
        state2 = new State2(drone, context);
        state3 = new State3(drone, context);
        state4 = new State4(drone, context);
        state5 = new State5(drone, context);
        state6 = new State6(drone, context);

        context.setState(state1);
    }

    public String getNextMove() {
        String nextMove = context.getNextMove();
        if(nextMove.equals("hold")){    // if you couldn't get a move from the last state, but rather only a transition to a 
                                                // next state where u CAN get the move, then just transition and get the move from the 
                                                //next state instead. Maybe less hard code hold but it's fine for now. we could add it as a cmd or smth.
            transition();
            nextMove = context.getNextMove();
            return nextMove;
        }else{
            transition();
            return nextMove;
        }

    }
    
    private void transition() {
        String nextString = context.getNextState();
        State next = null;

        switch(nextString){
            case "state1":
                next = state1;
            case "state2":
                next = state2;
            case "state3":
                next = state3;
            case "state4":
                next = state4;
            case "state5":
                next = state5;
            default:
                next = state6;
        }

        if(next != null){
            context.setState(next);
        }
    }
}
