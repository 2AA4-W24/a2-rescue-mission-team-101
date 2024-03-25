package ca.mcmaster.se2aa4.island.team101;
import java.lang.Math;
import java.util.List; 
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AreaMap {
    private final Logger logger = LogManager.getLogger();

    private Point emergencyPoint;

    private Map<Point, String> creekMap;
    
    public AreaMap(){
        creekMap = new HashMap<Point, String>();
    }

    public void updateMap(Point currentPosition, Response response, Command latestCommand){
        String type = latestCommand.getType();
        if(type.equals("scan")){
            ScanResponse scanResponse = (ScanResponse)response;
            Tile tile = new Tile();
            Point p = new Point(currentPosition.x, currentPosition.y);
            // need to make new point otherwise code breaks
            tile.fillTile((ScanResponse)response);
            if ((scanResponse.getCreeks().length()) > 0){
                logger.info(tile.getCreekID() + "creek id" + currentPosition);
                creekMap.put(p, tile.getCreekID());
                logger.info((Arrays.asList(creekMap)) + "creekmap");
            }
            if (tile.getSiteID() != null){
                logger.info(tile.getSiteID() + "site id" + p);
                emergencyPoint = p;
            }
        }
    }

    public String findClosestCreek(){

        Point closestInlet = new Point(0,0);
        double minDist = Integer.MAX_VALUE, distx, disty, calc_dist;
        if (emergencyPoint == null){
            List<String> valuesList = new ArrayList<String>(creekMap.values());
            return valuesList.get(0);
        }
        for (HashMap.Entry<Point, String> entry : creekMap.entrySet()){
            Point key = entry.getKey();
            String id = entry.getValue();
     
                
            distx = emergencyPoint.getX() - key.getX();
            disty = emergencyPoint.getY() - key.getY();
            calc_dist = Math.pow((Math.pow(distx, 2) + Math.pow(disty, 2)), 0.5);

            if (calc_dist <= minDist){
                minDist = calc_dist;
                closestInlet = key;
            }
                        
        }
        // find the closest creek using the creekmap
        // case where no closest creek is found is NOT ACCOUNTED FOR (i.e. closestInlet is null)
        return creekMap.get(closestInlet);
    }
}