
package entity;

public class Territories {

    private String TerritoryID, TerritoryDescription;
    private int RegionID;

    public Territories() {
    }

    public Territories(String TerritoryID, String TerritoryDescription, int RegionID) {
        this.TerritoryID = TerritoryID;
        this.TerritoryDescription = TerritoryDescription;
        this.RegionID = RegionID;
    }

    public String getTerritoryID() {
        return TerritoryID;
    }

    public void setTerritoryID(String TerritoryID) {
        this.TerritoryID = TerritoryID;
    }

    public String getTerritoryDescription() {
        return TerritoryDescription;
    }

    public void setTerritoryDescription(String TerritoryDescription) {
        this.TerritoryDescription = TerritoryDescription;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int RegionID) {
        this.RegionID = RegionID;
    }

}
