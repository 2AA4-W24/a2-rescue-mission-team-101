// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

public class StateUTurnEchoAnalyzer1 extends State{
    
    private EchoResponse latestEcho;
    private String found;
    private int range;

    public StateUTurnEchoAnalyzer1(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        found = latestEcho.getFound();
        range = latestEcho.getRange();
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        compass.advance();
        command.fly();
        return command;

    } 

    @Override
    public String getNextState(){

        if (found.equals("GROUND")){
            context.distanceToLand = range;
            return "StateFlyForward";
        } else {
            context.distanceToEdge = range;
            if (range > 2){
                return "StateExecuteUTurn";
            } else {
                return "StateGoHome";
            }
        }
    }


}