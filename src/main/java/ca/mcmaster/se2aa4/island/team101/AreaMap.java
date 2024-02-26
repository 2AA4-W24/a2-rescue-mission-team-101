package ca.mcmaster.se2aa4.island.team101;
import java.util.List; // keep it general ig
import java.util.ArrayList;

public class AreaMap {
    private List<String> creeks; //ids will be strings I suppose
    private String emergencySite;
    private Tile[][] map;

    public AreaMap(){
        this.creeks = new ArrayList<String>(); // implementing it as arraylist but methods will work if u choose to change it here
        // idk if this is a stupid idea or not or if we should just put it as an arraylist from the start here
    }

    public void addCreek(String creekID){
        creeks.add(creekID);
    }

    public void setEmergencySite(String emergencySiteID){
        emergencySite = emergencySiteID;
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
