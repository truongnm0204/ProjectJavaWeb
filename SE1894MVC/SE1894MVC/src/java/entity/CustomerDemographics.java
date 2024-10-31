
package entity;

public class CustomerDemographics {

    private String CustomerTypeID, CustomerDesc;

    public CustomerDemographics() {
    }

    public CustomerDemographics(String CustomerTypeID, String CustomerDesc) {
        this.CustomerTypeID = CustomerTypeID;
        this.CustomerDesc = CustomerDesc;
    }

    public String getCustomerTypeID() {
        return CustomerTypeID;
    }

    public void setCustomerTypeID(String CustomerTypeID) {
        this.CustomerTypeID = CustomerTypeID;
    }

    public String getCustomerDesc() {
        return CustomerDesc;
    }

    public void setCustomerDesc(String CustomerDesc) {
        this.CustomerDesc = CustomerDesc;
    }

}
