// LOOKING FOR LAND...? WITH THE DIFFERENT STAGES
//          ECHO RIGHT -> FLY OR CHANGE DIRECTION -> ECHO LEFT -> FLY OR CHANGE DIRECTION

package ca.mcmaster.se2aa4.island.team101;

public class State5 extends State{

    private String found;
    private int range;
    private EchoResponse latestEcho;

    public State5(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
            found = latestEcho.getFound();
            range = latestEcho.getRange();
    }

    @Override
    public String getNextMove(){

        if (context.stage%4==0){

            context.stage++;
            command.echo(compass.getRight());
            return command.toString();

        }else if(context.stage%4==1){

            context.stage++;

            if(found.equals("GROUND")){
                compass.turnRight();
                command.heading(compass.getDirection());
                context.distanceToLand = range;
                context.facingLand = true;
                return command.toString();
            }

            command.fly();
            context.distanceToEdge++;
            return command.toString();

        }else if(context.stage%4==2){

            context.stage++;
            command.echo(compass.getLeft());
            return command.toString();

        }else{

            context.stage++;

            // This code happens a few times could be consolidated 
            if(found.equals("GROUND")){
                compass.turnLeft();
                command.heading(compass.getDirection());
                context.distanceToLand = range;
                context.facingLand = true;
                return command.toString();
            }

            command.fly();
            context.distanceToEdge++;

        }

        return command.toString();

    }

    @Override
    public String getNextState(){
        return "state1"; 
    }
    
}