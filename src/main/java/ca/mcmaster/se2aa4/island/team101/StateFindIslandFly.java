// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;



public class StateFindIslandFly extends State{

    public StateFindIslandFly(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();

        if (context.left){
            context.left = false;
            command.echo(compass.getRight());
            return command;
        }

        
        else if (!context.left){

            context.left = true;
            command.echo(compass.getLeft());

            return command;
        }

    
        return command;

    } 

    @Override
    public String getNextState(){
        return "StateFindIslandEcho";
    }

}