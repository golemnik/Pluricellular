package com.golem.userModule;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    private List<String> clients = new ArrayList<>();

    public Clients () {

    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}
