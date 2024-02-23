package ca.mcmaster.se2aa4.island.team101;

public class Battery {
    
    private int batteryLevel;
    
    public Battery(){ 
    }

    public int getCharge(){
        return batteryLevel;
    }
    public void setCharge(int newCharge){
        this.batteryLevel = newCharge;
    }

}   

