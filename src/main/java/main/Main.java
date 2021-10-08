package main;


import http.HttpHandler;
import models.Customer;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private HttpHandler httpHandler;

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }


    public void menu() {
        System.out.println("  ********** ************  ************ ***********");
        System.out.println("\u001B[33m" + "     Welcome to our shop website services" + "\u001B[0m");
        System.out.println(" *********** ************  ************ ************");
        System.out.println("\u001B[33m" + "      Please choose one of these options:" + "\u001B[0m");
        System.out.println("============ ============  ============ =============");
        System.out.println("|| 1-Show all customers                            ||");
        System.out.println("|| 2-Show the information about a specific customer||");
        System.out.println("|| 3-Update a specific customer address            ||");
        System.out.println("|| 4-Exit                                          ||");
        System.out.println("============ ============  ============ =============");

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Please choose 1,2,3 or 4");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            httpHandler = new HttpHandler();
            switch (choice) {
                case 1: {
                    getAllCustomers();
                    run = false;
                    break;
                }
                case 2: {
                    getOneCustomer();
                    run = false;
                    break;
                }
                case 3: {
                    updateCustomer();
                    run = false;
                    break;
                }
                case 4: {
                    System.exit(0);
                }
                default: {
                    System.out.println("Pleas choose a number");
                    run = false;
                    break;
                }
            }
        }
        System.out.println();
        menu();

    }

    //Return all customers in the server (From the Database)
    public void getAllCustomers() {
        ArrayList<Customer> customers = httpHandler.getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("\u001B[33m" + "            Customer with ID:" + (i + 1) + "\u001B[0m");
            printCustomerInfo(customer);
        }
    }

    //Update one specific customer
    public void updateCustomer() {
        Customer c = getOneCustomer();
        if (c != null) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("PLease enter the new Street Address");
            String input = scanner1.nextLine();
            c.setAddress(input);

            System.out.print("Please enter the post Number");
            input = scanner1.nextLine();
            c.setPostNumber(input);

            System.out.print("Please enter the city");
            input = scanner1.nextLine();
            c.setCityName(input);

            Response response = httpHandler.updateCustomer(c.getSsn(), c);
            if (response.getStatus() == 200) {
                System.out.println("Customer information: " + c.getSsn() + "\n");
                printCustomerInfo(response.readEntity(Customer.class));
            } else {
                System.out.println("\u001B[31m" + response.getEntityTag() + "\u001B[0m");
            }
        }
    }

    //Print customer information
    public void printCustomerInfo(Customer customers) {
        System.out.println("********** ********** ********** **********");
        System.out.println("\u001B[33m" + "         Customer information" + "\u001B[0m");
        System.out.println("********** ********** ********** **********");
        System.out.println("Customer SSN: " + customers.getSsn());
        System.out.println("Customer's name: " + customers.getName());
        System.out.println("Customer gender: " + customers.getGender());
        System.out.println("Customer street address: " + customers.getAddress());
        System.out.println("Post Number: " + customers.getPostNumber());
        System.out.println("City: " + customers.getCityName());

        for (int i = 0; i < customers.getOrders().size(); i++) {
            System.out.println("********** ********** ********** **********");
            System.out.println("\u001B[33m" + "          Customer Orders" + "\u001B[0m");
            System.out.println("********** ********** ********** **********");
            System.out.println("Order id: " + customers.getOrders().get(i).getId());
            System.out.println("Order date: " + customers.getOrders().get(i).getDate());
            System.out.println("Order total price: " + customers.getOrders().get(i).getTotalPrice());
            System.out.println("Order payment status: " + customers.getOrders().get(i).getStatus());
            System.out.println("\n");
        }
    }


    // Return specific customer

    public Customer getOneCustomer() {
        System.out.println("********** ********** ********** **********");
        System.out.println("please enter the customer ssn in this form (xxxxxxxx-xxxx)");

        Scanner scanner1 = new Scanner(System.in);
        String ssn = scanner1.nextLine();

        // Get response from the server using HTTP
        Response response = httpHandler.getCustomer(ssn);
        Customer c = null;
        if (response.getStatus() == 200) {
            c = response.readEntity(Customer.class);
            System.out.println();
            printCustomerInfo(c);
        } else {
            System.out.println(response.getEntityTag());
        }
        return c;
    }
}