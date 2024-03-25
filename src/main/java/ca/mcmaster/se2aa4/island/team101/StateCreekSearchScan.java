// ECHO FORWARD <-- INITIAL STATE
// this never uses the context because it doesn't matter; regardless of the context this first state needs
// to just echo forward

package ca.mcmaster.se2aa4.island.team101;



public class StateCreekSearchScan extends State{
    
    private ScanResponse latestScan;
    private String found;
    private Boolean onlyOcean;

    public StateCreekSearchScan(Drone drone, DroneContext context){ 
        super(drone, context); 
        latestScan = (ScanResponse) latestResponse;
        onlyOcean = latestScan.isAllOcean();

    }

    @Override
    public Command getNextMove(){
        this.command = new Command();
        compass.advance();
        command.fly();
        return command;

    } 

    @Override
    public String getNextState(){
        if (onlyOcean){
            return "StateUTurnEcho1";
        } else {
            return "StateCreekSearchFly";
        }
    }

}