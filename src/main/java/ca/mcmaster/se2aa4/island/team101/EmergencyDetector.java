package ca.mcmaster.se2aa4.island.team101;

public class EmergencyDetector {
    private Decision decisionToCheck; 

    public EmergencyDetector(Decision decision){
        // you can pass it an air or land decision cuz of polymorph
        decisionToCheck = decision; // not even sure if the status is ever useful just leaving it for now
    }

    // MIA related things
    // using these in drone you can check if itll go MIA for various reasons\
    // each one probably needs to get handled differently so u can use each diff method to see
    // why it's going MIA, not just if it went MIA

    public Boolean goesOutOfRange(){
        return false; 
    }

    public Boolean batteryDies(){
        return false; 
    }

    public Boolean badCommand(){
        return false; 
    }
}
