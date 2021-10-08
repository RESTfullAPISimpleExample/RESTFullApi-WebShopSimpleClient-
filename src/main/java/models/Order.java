package models;


import java.io.Serializable;
import java.util.UUID;

public class Order implements Serializable {

    //Using UUUID to generate a unique id number
    private UUID id;
    private String Date;
    private double totalPrice;
    private String status;
    private Customer customer;

    public enum Status {
        Payed,
        NotPayed
    }

    public Order() {
    }

    public Order(String date, double totalPrice, String status) {
        this.id = UUID.randomUUID();
        this.Date = date;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customer = new Customer();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
