// YOU JUST SAW NO GROUND, AND GOT THE EDGE <-- LOOK FOR LAND

package ca.mcmaster.se2aa4.island.team101;

public class State3 extends State{

    public State3(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public String getNextMove(){

        if(context.distanceToEdge < context.edge){
            command.fly();
            context.eta++;
            return command.toString();

        }else if(context.facingLand){
            context.atLand = true;
        }

        if(context.atLand && !context.scanComplete){
            command.scan();
            context.scanComplete = true;
            return command.toString();
        }

        if(context.scanComplete){
            command.hold();
        }

        return command.toString();
    }

    @Override
    public String getNextState(){
        if(context.scanComplete){
            return "state 4";
        }else{
            return "state 5";
        }
    }

    
}