package ca.mcmaster.se2aa4.island.team101;

public class Tile {
    private String creekID;
    private String siteID;

    public Tile(){
        this.creekID = "";
        this.siteID = "";
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

    public void fillTile(ScanResponse scanResponse) {
        String newCreekID = scanResponse.getCreek();
        if (!newCreekID.isEmpty()) {
            this.creekID = newCreekID;
        }
        String newSiteID = scanResponse.getSite();
        if (!newSiteID.isEmpty()) {
            this.siteID = newSiteID;
        }
    }
}