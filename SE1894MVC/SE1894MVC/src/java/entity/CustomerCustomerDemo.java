
package entity;

public class CustomerCustomerDemo {

    private String CustomerID,
            CustomerTypeID;

    public CustomerCustomerDemo() {
    }

    public CustomerCustomerDemo(String CustomerID, String CustomerTypeID) {
        this.CustomerID = CustomerID;
        this.CustomerTypeID = CustomerTypeID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerTypeID() {
        return CustomerTypeID;
    }

    public void setCustomerTypeID(String CustomerTypeID) {
        this.CustomerTypeID = CustomerTypeID;
    }

}
