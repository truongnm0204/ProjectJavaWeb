
package entity;

public class Order {

    private int OrderID;
    private String CustomerID;
    private int EmployeeID;
    private String OrderDate, RequiredDate, ShippedDate;
    private int ShipVia;
    private double Freight;
    private String ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry;

    public Order() {
    }

    public Order(int OrderID, String CustomerID, int EmployeeID, String OrderDate, String RequiredDate, String ShippedDate, int ShipVia, double Freight, String ShipName, String ShipAddress, String ShipCity, String ShipRegion, String ShipPostalCode, String ShipCountry) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.ShipVia = ShipVia;
        this.Freight = Freight;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCity = ShipCity;
        this.ShipRegion = ShipRegion;
        this.ShipPostalCode = ShipPostalCode;
        this.ShipCountry = ShipCountry;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(String RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    public String getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(String ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public int getShipVia() {
        return ShipVia;
    }

    public void setShipVia(int ShipVia) {
        this.ShipVia = ShipVia;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(double Freight) {
        this.Freight = Freight;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String ShipName) {
        this.ShipName = ShipName;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    public String getShipCity() {
        return ShipCity;
    }

    public void setShipCity(String ShipCity) {
        this.ShipCity = ShipCity;
    }

    public String getShipRegion() {
        return ShipRegion;
    }

    public void setShipRegion(String ShipRegion) {
        this.ShipRegion = ShipRegion;
    }

    public String getShipPostalCode() {
        return ShipPostalCode;
    }

    public void setShipPostalCode(String ShipPostalCode) {
        this.ShipPostalCode = ShipPostalCode;
    }

    public String getShipCountry() {
        return ShipCountry;
    }

    public void setShipCountry(String ShipCountry) {
        this.ShipCountry = ShipCountry;
    }

}
