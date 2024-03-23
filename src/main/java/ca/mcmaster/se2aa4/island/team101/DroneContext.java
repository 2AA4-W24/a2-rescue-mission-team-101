package ca.mcmaster.se2aa4.island.team101;

public class DroneContext implements State{

    private State droneState;

    public void setState(State state){
        this.droneState = state;
    }

    public State getState(){
        return this.droneState;
    }

    @Override
    public void decide(){
        this.droneState.decide();
    }

}