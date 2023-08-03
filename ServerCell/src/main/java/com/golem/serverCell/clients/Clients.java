package com.golem.serverCell.clients;

import java.util.HashMap;
import java.util.Map;

public class Clients {
    private static Clients instance;
    private Map<String, RegClient> clients = new HashMap<>();

    public static synchronized Clients getInstance() {
        if (instance == null){
            instance = new Clients();
        }
        return instance;
    }

    public Clients() {
    }

    public void addUser (String login, String password) {
        clients.put(login, new RegClient(password));
    }

    public boolean checkClient (String login, String password) {
        if (clients.get(login) == null) return false;
        return clients.get(login).getPassword().equals(password);
    }

    public boolean existClient (String login) {
        return clients.get(login) != null;
    }

    public boolean checkStatus (String login) {
        if (clients.get(login) == null) return false;
        return clients.get(login).getStatus();
    }

    public void updateStatus (String login) {
        clients.get(login).setStatus(!clients.get(login).getStatus());
    }

    public Map<String, RegClient> getClients() {
        return clients;
    }

    public void setClients(Map<String, RegClient> clients) {
        this.clients = clients;
    }
}
