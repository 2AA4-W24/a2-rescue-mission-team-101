package ca.mcmaster.se2aa4.island.team101;

public class EmergencyDetector {
    private String status; 
    private JSONResponse response;

    public EmergencyDetector(String initialStatus){
        setStatus(initialStatus); // not even sure if the status is ever useful just leaving it for now
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Boolean isMIA(){
        // Need to check battery, make sure it's in range, bad command (no sudden u-turn)
        return false;
    }
}
