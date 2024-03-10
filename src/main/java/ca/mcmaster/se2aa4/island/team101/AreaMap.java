package ca.mcmaster.se2aa4.island.team101;
import java.util.List; // keep it general ig
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class AreaMap {
    List<String> rows = new ArrayList<String>();
    HashMap<Point, Tile> map = new HashMap<Point, Tile>();

    public AreaMap(){
    }

    public void addCreek(String creekID){

    }

    public void setEmergencySite(String emergencySiteID){
    }

    public List<String> getCreeks(){
    }

    public String getEmergencySite(){
    }
    // drone can use these when it calls a stop command
    // then drone can calculate the distance from each creek to emerg. site and pick shortest to return
}
