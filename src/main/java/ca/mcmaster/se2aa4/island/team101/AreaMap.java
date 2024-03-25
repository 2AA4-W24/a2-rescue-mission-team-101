package ca.mcmaster.se2aa4.island.team101;
import java.util.List; 
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.awt.Point;

public class AreaMap {

    private Point emergencyPoint;

    private Map<Point, String> creekMap;
    
    public AreaMap(){
        creekMap = new HashMap<Point, String>();
    }

    public void updateMap(Point currentPosition, Response response, Command latestCommand){
        String type = latestCommand.getType();
        if("scan".equals(type)){
            ScanResponse scanResponse = (ScanResponse)response;
            Tile tile = new Tile();
            Point p = new Point(currentPosition.x, currentPosition.y);
            // need to make new point otherwise code breaks
            tile.fillTile((ScanResponse)response);
            if (scanResponse.getCreeks().length() > 0){
                creekMap.put(p, tile.getCreekID());
            }
            if (tile.getSiteID() != null){
                emergencyPoint = p;
            }
        }
    }

    public String findClosestCreek(){

        Point closestInlet = new Point(0,0);
        double minDist = Integer.MAX_VALUE;
        double distX;
        double distY;
        double calcDist;
        if (emergencyPoint == null){
            List<String> valuesList = new ArrayList<String>(creekMap.values());
            return valuesList.get(0);
        }
        for (HashMap.Entry<Point, String> entry : creekMap.entrySet()){
            Point key = entry.getKey();     
            distX = emergencyPoint.getX() - key.getX();
            distY = emergencyPoint.getY() - key.getY();
            calcDist = Math.pow(Math.pow(distX, 2) + Math.pow(distY, 2), 0.5);

            if (calcDist <= minDist){
                minDist = calcDist;
                closestInlet = key;
            }
                        
        }
        // find the closest creek using the creekmap
        // case where no closest creek is found is NOT ACCOUNTED FOR (i.e. closestInlet is null)
        return creekMap.get(closestInlet);
    }
}