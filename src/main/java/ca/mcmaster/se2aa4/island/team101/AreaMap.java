package ca.mcmaster.se2aa4.island.team101;
import java.util.List; // keep it general ig
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class AreaMap {
    List<String> rows = new ArrayList<String>();
    HashMap<Point, Tile> map;

    public AreaMap(){
        map = new HashMap<>();
    }

    // feed it the drone's position at the time of the scan as the point
    // and the tile will uh be made from a scan hmm
    private void addTile(Point p, Tile tile) {
        map.put(p, tile);
    }

    // dunno if we'll ever need this. maybe when looking for creeks and site in the end.
    public Tile getTile(Point p) {
        return map.get(p);
    }

    // so when u move u hand off the coordinates and the response
    // and it'll update the tile accordingly and put it in the map
    public void updateMap(Point currentPosition, ScanResponse scan){
        Tile tile = new Tile();
        tile.fillTile(scan);
        addTile(currentPosition, tile);
    }

    // not gonna add one to return a list of creeks cuz u need their points to do the distance calculation
    // which means its prob better to just grab them out of the map directly so u can see the IDs and the points
    // together. so we can just loop through the map looking for creek tiles when the time comes to check the 
    // distances with emergencysite. you also need the point of emergencysite (not the ID) to do the calc. Best to
    // leave it in hash.

    // then drone?(ig)? can calculate the distance from each creek to emerg. site and pick shortest to return
}
