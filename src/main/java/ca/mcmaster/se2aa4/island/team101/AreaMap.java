package ca.mcmaster.se2aa4.island.team101;
import java.util.List; // keep it general ig
import java.util.ArrayList;

public class AreaMap {
    private List<String> creeks; //ids will be strings I suppose
    private String emergencySite;

    public AreaMap(){
        this.creeks = new ArrayList<String>(); // implementing it as arraylist but methods will work if u choose to change it here
    }

    public List<String> getCreeks(){
        return creeks;
    }

    public String getEmergencySite(){
        return emergencySite;
    }
    // drone can use these when it calls a stop command
    // then drone can calculate the distance from each creek to emerg. site and pick shortest to return
}
