package ca.mcmaster.se2aa4.island.team101;

import org.json.JSONArray;

public class Tile {
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

        JSONArray creeks = scan.getCreeks();
        if (creeks.length() > 0) {
            StringBuilder creekIDs = new StringBuilder();
            for (int i = 0; i < creeks.length(); i++) {
                creekIDs.append(creeks.getString(i));
                if (i < creeks.length() - 1) {
                    creekIDs.append(", "); // comma separated IDs
                }
            }
            this.creekID = creekIDs.toString();
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