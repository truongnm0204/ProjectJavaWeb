/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84968
 */
public class Bill {

    private int OrderID;
    private String OrderDate;
    private String RequiredDate;
    private String ShippedDate;
    private String ContactName;
    private String Address;
    private String Phone;
    private String Fax;
    private int ProductID;
    private String ProductName;
    private double UnitPrice;
    private int Quantity;
    private double Discount;

    public Bill() {
    }

    public Bill(int OrderID, String OrderDate, String RequiredDate, String ShippedDate, String ContactName, String Address, String Phone, String Fax, int ProductID, String ProductName, double UnitPrice, int Quantity, double Discount) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.ContactName = ContactName;
        this.Address = Address;
        this.Phone = Phone;
        this.Fax = Fax;
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Discount = Discount;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
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

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

}
