package models;


import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String ssn;
    private String name;
    private String address;
    private String postNumber;
    private String cityName;
    private String gender;
    private ArrayList<Order> orders;

    public enum Gender {
        Male,
        Female
    }

    public Customer() {
    }

    public Customer(String ssn, String name, String gender, String address, String postNumber, String cityName) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.postNumber = postNumber;
        this.cityName = cityName;
        this.orders = new ArrayList<>();
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
