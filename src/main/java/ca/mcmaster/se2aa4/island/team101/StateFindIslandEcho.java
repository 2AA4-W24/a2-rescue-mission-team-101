// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;



public class StateFindIslandEcho extends State{
    
    private EchoResponse latestEcho;
    private String found;
    private int range;

    public StateFindIslandEcho(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
        found = latestEcho.getFound();
        range = latestEcho.getRange();
        
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();

        if (context.left){
            if (found.equals("GROUND")){ // this will always be false for the first echo
                compass.turnLeft();
                command.heading(compass.getDirection());
            } else {
                compass.advance();
                command.fly();
                context.distanceToEdge--;
            }
            return command;
        }

        
        if (!context.left){
            if (found.equals("GROUND")){
                compass.turnRight();
                command.heading(compass.getDirection());
            } else {
                compass.advance();
                command.fly();
                context.distanceToEdge--;
            }
            return command;
        }
    
        return command;

    } 

    @Override
    public String getNextState(){
        if (found.equals("GROUND")){
            context.distanceToLand = range;
            return "StateFlyForward";
        } else {
            context.edge = range;
            return "StateFindIslandFly";
        }
    }

}