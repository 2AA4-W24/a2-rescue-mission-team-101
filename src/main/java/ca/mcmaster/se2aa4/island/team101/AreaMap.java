package ca.mcmaster.se2aa4.island.team101;
import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class AreaMap {
    List<String> rows = new ArrayList<String>();
    HashMap<Point, Tile> map;
    
    public AreaMap(){
        map = new HashMap<>();
    }

    private void addTile(Point p, Tile tile) {
        map.put(p, tile);
    }

    public Tile getTile(Point p) {
        return map.get(p);
    }

    public void updateMap(Point currentPosition, Response response){
        String type = response.getType();
        if(type.equals("scan")){
            Tile tile = new Tile();
            tile.fillTile((ScanResponse)response);
            addTile(currentPosition, tile);
        }
    }

    // return cleost creek use pytehon throem
}