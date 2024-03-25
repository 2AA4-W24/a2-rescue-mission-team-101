package ca.mcmaster.se2aa4.island.team101;


public abstract class State{

    protected Command command;
    protected Compass compass;
    protected Response latestResponse;
    protected DroneContext context;
    public State(Drone drone, DroneContext context){
        this.command = new Command();
        this.compass = drone.getCompass();
        this.latestResponse = drone.getLatestResponse();
        this.context = context;

    }

    public abstract Command getNextMove();
    public abstract String getNextState();

}