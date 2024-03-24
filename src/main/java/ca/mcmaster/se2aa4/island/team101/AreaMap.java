package ca.mcmaster.se2aa4.island.team101;
import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.lang.Math;

public class AreaMap {
    List<String> rows = new ArrayList<String>();
    HashMap<Point, Tile> map;
    private Point emergencyPoint;
    
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

            if (tile.getSiteID() != null){
                emergencyPoint = currentPosition;
            }
        }
    }

    public String FindClosestCreek(){
        Point closestInlet = new Point();
        double minDist = 100000, distx, disty, calc_dist;

        for (Point i: map.keySet()){
            Tile tile = map.get(i);
            if (tile.getCreekID()!=null && tile.getSiteID()==null){
                distx = emergencyPoint.getX() - i.getX();
                disty = emergencyPoint.getY() - i.getY();
                calc_dist = Math.pow((Math.pow(distx, 2) + Math.pow(disty, 2)), 0.5);
                if (calc_dist <= minDist){
                    minDist = calc_dist;
                    closestInlet = i;
                }
            }
        }
        // case where no closest creek is found is NOT ACCOUNTED FOR (i.e. closestInlet is null)
        return map.get(closestInlet).getCreekID();
    }
}