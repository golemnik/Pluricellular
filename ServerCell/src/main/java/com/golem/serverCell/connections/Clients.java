package com.golem.serverCell.connections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clients {
    private Map<String, String> clients = new HashMap<>();

    public Clients() {

    }

    public boolean checkClient (String login, String password) {
        if (clients.get(login) == null) return false;
        return clients.get(login).equals(password);
    }

    public Map<String, String> getClients() {
        return clients;
    }

    public void setClients(Map<String, String> clients) {
        this.clients = clients;
    }
}
