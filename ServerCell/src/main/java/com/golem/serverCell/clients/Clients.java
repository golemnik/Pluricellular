package com.golem.serverCell.clients;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.innerMechanics.Crypt;

import java.util.HashMap;
import java.util.Map;

public class Clients {
    private static Clients instance;
    private Map<String, String> clients = new HashMap<>();

    public static synchronized Clients getInstance() {
        if (instance == null){
            instance = new Clients();
        }
        return instance;
    }

    public Clients() {

    }

    public void addUser (String login, String password) {
        clients.put(login, password);
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
