// U-TURN STATE

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class State6 extends State{

    private String found;
    private int range;
    private EchoResponse latestEcho;

    private final Logger logger = LogManager.getLogger();


    public State6(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestEcho = (EchoResponse) latestResponse;
            found = latestEcho.getFound();
            range = latestEcho.getRange();
    }

    @Override
    public String getNextMove(){

        logger.info("****************IN STATE 6 GETNEXTMOVE******************");

            if (context.lastTurnRight){
                // 1/2 turn RIGHT
                if (context.fly){
                    command.fly();
                    context.distanceToLand--;
                    if (context.distanceToLand <= 0){
                        context.lastTurnRight = false;
                        context.activateUTurn = false;
                        context.turn = 0;
                        context.fly = false;
                    }
                    return command.toString();
                }
                if (context.turn==0){
                    compass.turnRight();
                    command.heading(compass.getDirection());
                    context.turn++;
                    return command.toString();
                }
                else if(context.turn==1){
                    compass.turnRight();
                    command.heading(compass.getDirection());
                    context.turn++;
                    return command.toString();
                }
                else if (context.turn==2){
                    command.echo(compass.getDirection());
                    context.turn++;
                    return command.toString();
                } else if (context.turn == 3){
                    if (found.equals("GROUND")){
                        context.distanceToLand = range;
                        if (context.distanceToLand > 0){
                            context.fly = true;
                            context.distanceToLand--;
                        } else {
                            context.turn = 0;
                            context.lastTurnRight = false;
                            context.activateUTurn = false;
                        }
                            command.fly();
                        return command.toString();
                    } else {
                        command.stop();
                        return command.toString();
                    }
                }
            }
    
            else{
                // 1/2 turn LEFT
                if (context.fly){
                    command.fly();
                    context.distanceToLand--;
                    if (context.distanceToLand <= 0){
                        context.lastTurnRight = true;
                        context.activateUTurn = false;
                        context.turn = 0;
                        context.fly = false;
                    }
                    return command.toString();
                }
                if (context.turn==0){
                    compass.turnLeft();
                    command.heading(compass.getDirection());
                    context.turn++;
                    return command.toString();
                }
                else if(context.turn==1){
                    compass.turnLeft();
                    command.heading(compass.getDirection());
                    context.turn++;
                    return command.toString();
                }
                else if (context.turn==2){
                    command.echo(compass.getDirection());
                    context.turn++;
                    return command.toString();
                } else if (context.turn == 3){
                    if (found.equals("GROUND")){
                        context.distanceToLand = range;
                        if (context.distanceToLand > 0){
                            context.fly = true;
                            context.distanceToLand--;
                        } else {
                            context.turn = 0;
                            context.lastTurnRight = true;
                            context.activateUTurn = false;
                        }
                        command.fly();
                        return command.toString();
                    } else {
                        command.stop();
                        return command.toString();
                    }
                }
            }
            return command.toString();
        }

    @Override
    public String getNextState(){
        return "state1";
    }

}