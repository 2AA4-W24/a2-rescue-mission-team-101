// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import javax.naming.Context;


public class StateExecuteUTurn extends State{
    
    public StateExecuteUTurn(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        if (!context.lastTurnRight){
            if (context.uTurnStage == 0){
                compass.turnRight();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if(context.uTurnStage == 1){
                compass.turnRight();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 2){
                command.echo(compass.getDirection());
                context.uTurnStage++;
                context.lastTurnRight = true;
                return command;
            } 
        }

        if (context.lastTurnRight){
            if (context.uTurnStage == 0){
                compass.turnLeft();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if(context.uTurnStage == 1){
                compass.turnLeft();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 2){
                command.echo(compass.getDirection());
                context.uTurnStage++;
                context.lastTurnRight = false;
                return command;
            } 
        }
        

        return command;
    } 

    @Override
    public String getNextState(){
        this.command = new Command();

        if (context.uTurnStage <= 2){
            return "StateExecuteUTurn";
        } else {
            context.uTurnStage = 0;
            return "StateUTurnEcho2";
        }
    }


}