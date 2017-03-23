package com.example.chiwaura.blesscoffee;

/**
 * Created by blessing on 1/21/2017.
 */

public class OrderTransactions {

    private String customerDetails;
    private String orderedItems;
    private String paymentMethod;
    private String dateTimeOrdered;
    private String amountDue;

    public OrderTransactions() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }



    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }



    public String getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(String orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getDateTimeOrdered() {
        return dateTimeOrdered;
    }

    public void setDateTimeOrdered(String dateTimeOrdered) {
        this.dateTimeOrdered = dateTimeOrdered;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

