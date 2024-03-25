// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateSpecialUTurn extends State{
    
    private final Logger logger = LogManager.getLogger();


    public StateSpecialUTurn(Drone drone, DroneContext context){ 
        super(drone, context); 
    }

    @Override
    public Command getNextMove(){
        if (context.turned){
            command.stop();
            return command;
        }
        this.command = new Command();
        logger.info("U TURN STAGE: !!! " + context.uTurnStage);
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
                command.fly();
                context.uTurnStage++;
                return command;
            } 
            else if (context.uTurnStage == 3){
                compass.turnRight();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 4){
                command.fly();
                context.uTurnStage++;
                return command;
            }  
            else if (context.uTurnStage == 5){
                command.fly();
                context.uTurnStage++;
                return command;
            }  
            else if (context.uTurnStage == 6){
                command.fly();
                context.uTurnStage++;
                return command;
            }  
            else if (context.uTurnStage == 7){
                compass.turnRight();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 8){
                command.echo(compass.getDirection());     
                context.uTurnStage++;          
                context.lastTurnRight = true;
                context.turned = true;
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
                command.fly();
                context.uTurnStage++;
                return command;
            } 
            else if (context.uTurnStage == 3){
                compass.turnLeft();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 4){
                command.fly();
                context.uTurnStage++;
                return command;
            }  
            else if (context.uTurnStage == 5){
                command.fly();
                context.uTurnStage++;
                return command;
            }  
            else if (context.uTurnStage == 7){
                compass.turnLeft();
                command.heading(compass.getDirection());
                context.uTurnStage++;
                return command;
            }
            else if (context.uTurnStage == 8){
                command.echo(compass.getDirection());
                context.uTurnStage++;
                context.lastTurnRight = false;
                context.turned = true;
                return command;
            }
        }
        command.echo(compass.getDirection());
        return command;
    } 

    @Override
    public String getNextState(){

        if (context.uTurnStage <= 8){
            return "StateSpecialUTurn";
        } else {
            context.uTurnStage = 0;
            return "StateSpecialUTurnEchoAnalyzer";
        }
    }


}