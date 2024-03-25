package ca.mcmaster.se2aa4.island.team101;
import java.lang.Math;
import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AreaMap {
    private final Logger logger = LogManager.getLogger();

    private Point emergencyPoint;

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
            if (tile.getSiteID() != null){
                logger.info(tile.getSiteID() + "site id");
            }
            if (tile.getSiteID() != null){
                emergencyPoint = currentPosition;
            }
        }
    }

    public String findClosestCreek(){

        Point closestInlet = new Point();
        double minDist = 100000, distx, disty, calc_dist;
        if (emergencyPoint == null){
            return "nothing";
        }
        for (HashMap.Entry<Point, Tile> entry : map.entrySet()){
            Point key = entry.getKey();
            Tile tile = entry.getValue();
            if (tile != null){

                
                if (!(tile.getCreekID() == (null))){
                    logger.info("in closest creek" + emergencyPoint.getX());
                    distx = emergencyPoint.getX() - key.getX();
                    disty = emergencyPoint.getY() - key.getY();
                    calc_dist = Math.pow((Math.pow(distx, 2) + Math.pow(disty, 2)), 0.5);
                    logger.info("in closest creek");
    
                    if (calc_dist <= minDist){
                        minDist = calc_dist;
                        closestInlet = key;
                    }
                }
                logger.info("in closest creek");
            }


        }
        // case where no closest creek is found is NOT ACCOUNTED FOR (i.e. closestInlet is null)
        return map.get(closestInlet).getCreekID();
    }

    // return cleost creek use pytehon throem
}