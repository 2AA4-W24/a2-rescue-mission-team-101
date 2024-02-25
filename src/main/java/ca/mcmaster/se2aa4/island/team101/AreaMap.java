package ca.mcmaster.se2aa4.island.team101;
import java.awt.Point; // might make calculations easier for distance when we wanna deal w it in drone
import java.util.List; // keep it general ig
import java.util.ArrayList;

public class AreaMap {
    private List<Point> creeks;
    private Point emergencySite;

    public AreaMap(){
        this.creeks = new ArrayList<Point>(); // implementing it as arraylist but methods will work if u choose to change it here
    }

    public List<Point> getCreeks(){
        return creeks;
    }

    public Point getEmergencySite(){
        return emergencySite;
    }
    // drone can use these when it calls a stop command
    // then drone can calculate the distance from each creek to emerg. site and pick shortest to return
}
