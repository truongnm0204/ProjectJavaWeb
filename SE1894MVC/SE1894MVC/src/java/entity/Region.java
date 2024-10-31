
package entity;

public class Region {

    private int RegionID;
    private String RegionDescription;

    public Region() {
    }

    public Region(int RegionID, String RegionDescription) {
        this.RegionID = RegionID;
        this.RegionDescription = RegionDescription;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int RegionID) {
        this.RegionID = RegionID;
    }

    public String getRegionDescription() {
        return RegionDescription;
    }

    public void setRegionDescription(String RegionDescription) {
        this.RegionDescription = RegionDescription;
    }

}
