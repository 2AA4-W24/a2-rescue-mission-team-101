package ca.mcmaster.se2aa4.island.team101;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

public class Tile {
    private final Logger logger = LogManager.getLogger();

    private String creekID;
    private String siteID;

    public Tile(){
        this.creekID = null;
        this.siteID = null;
    }

    public String getCreekID(){
        return creekID;
    }

    public void setCreekID(String creekID){
        this.creekID = creekID;
    }

    public String getSiteID(){
        return siteID;
    }

    public void setSiteID(String siteID){
        this.siteID = siteID;
    }

    public void fillTile(ScanResponse scan) {

        // if more than 1 creek in the tile, return only one becuase we cant tell which one is really closer
        JSONArray creeks = scan.getCreeks();
        if (creeks.length() > 0) {
            this.creekID = creeks.getString(0); 
        }

        // Site ID to is first site in the list it has 
        JSONArray sites = scan.getSites();
        if (sites.length() > 0) {
            this.siteID = sites.getString(0); 
        }

    }

    public boolean isEmpty(){
        return siteID == null && creekID == null;
    }
}