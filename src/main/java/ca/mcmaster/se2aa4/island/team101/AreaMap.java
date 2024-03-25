package ca.mcmaster.se2aa4.island.team101;
import java.lang.Math;
import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            tile.fillTile((ScanResponse)response);
            if ((scanResponse.getCreeks().length()) > 0){
                logger.info(tile.getCreekID() + "creek id" + currentPosition);
                creekMap.put(currentPosition, tile.getCreekID());
                logger.info((Arrays.asList(creekMap)) + "creekmap");
            }
            if (tile.getSiteID() != null){
                logger.info(tile.getSiteID() + "site id" + currentPosition);
                emergencyPoint = currentPosition;
            }
        }
    }

    public String findClosestCreek(){
        logger.info((Arrays.asList(creekMap)));

        for (Point p : creekMap.keySet()){
            logger.info(p + "point of creek");
        }
        Point closestInlet = new Point(0,0);
        double minDist = 100000, distx, disty, calc_dist;
        if (emergencyPoint == null){
            return "nothing";
        }

        // find the closest creek using the creekmap
        // case where no closest creek is found is NOT ACCOUNTED FOR (i.e. closestInlet is null)
        return creekMap.get(0);
    }
}