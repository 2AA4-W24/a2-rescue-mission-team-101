// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

public class StateUTurnEcho2 extends State{

    public StateUTurnEcho2(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();

        command.echo(compass.getDirection());
        return command;
    } 

    @Override
    public String getNextState(){
        return "StateUTurnEchoAnalyzer2";
    }

}