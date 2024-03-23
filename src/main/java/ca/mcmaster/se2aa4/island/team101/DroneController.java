package ca.mcmaster.se2aa4.island.team101;

public class DroneController{
    DroneContext context = new DroneContext();

    // Initial state
    State echoState = new EchoState(); 
    State edgeState = new EdgeState(); 
    State landState = new LandState();
    State creekState = new CreekState();
    State uTurnState = new UTurnState();

    private Command command = new Command(); // controller changes command which alerts drone

    public DroneController(){}

    public String getNextMove(){

        /** 
         * use a switch case to pick
         * 
        context.setState(echoState)
    
        context.setState(edgeState);
    
        context.setState(landState);
    
        context.setState(creekState);
    
        context.setState(uTurnState);
        */

        return context.decide();
    }

}