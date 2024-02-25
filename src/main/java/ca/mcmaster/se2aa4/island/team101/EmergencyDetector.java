package ca.mcmaster.se2aa4.island.team101;

public class EmergencyDetector {
    private String status; 
    private JSONResponse response;

    public EmergencyDetector(String initialStatus){
        setStatus(initialStatus);
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Boolean isMIA(){
        return false;
    }
}
