package http;


import models.Customer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class HttpHandler {

    private Client client;
    private String baseAddress = "http://localhost:7070/Lab2NetworkApplicationServer_war_exploded/api/customers";


    public HttpHandler() {
        client = ClientBuilder.newClient();
    }

    public ArrayList<Customer> getAllCustomers() {
        return client.target(baseAddress)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<ArrayList<Customer>>() {
                });

    }

    public Response getCustomer(String ssn) {
        return client.target(baseAddress).path("/{ssn}").resolveTemplate("ssn", ssn)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);
    }

    public Response updateCustomer(String ssn, Customer customer) {
        return client.target(baseAddress).path("/{ssn}").resolveTemplate("ssn", ssn)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(customer), Response.class);
    }
}
