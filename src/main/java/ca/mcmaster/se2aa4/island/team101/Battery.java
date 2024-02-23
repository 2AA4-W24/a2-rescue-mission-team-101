package ca.mcmaster.se2aa4.island.team101;

public class Battery {
    private int batteryLevel;
    private JSONResponse response;
    
    public Battery(Integer initialBattery){ 
        setCharge(initialBattery);
    }

    public int getCharge(){
        return batteryLevel;
    }

    public void setCharge(int charge){
        this.batteryLevel = charge;
    }

}   

