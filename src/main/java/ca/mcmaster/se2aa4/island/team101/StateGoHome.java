// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;


public class StateGoHome extends State{


    public StateGoHome(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        command.stop();
        return command;
    } 

    @Override
    public String getNextState(){
        return "StateGoHome";
    }

}