
package entity;

public class EmployeeTerritories {

    private int EmployeeID;
    private String TerritoryID;

    public EmployeeTerritories() {
    }

    public EmployeeTerritories(int EmployeeID, String TerritoryID) {
        this.EmployeeID = EmployeeID;
        this.TerritoryID = TerritoryID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getTerritoryID() {
        return TerritoryID;
    }

    public void setTerritoryID(String TerritoryID) {
        this.TerritoryID = TerritoryID;
    }

}
