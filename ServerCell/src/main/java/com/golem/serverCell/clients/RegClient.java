package com.golem.serverCell.clients;

public class RegClient {
    private final String password;
    private boolean status = false;

    public RegClient () {
        this.password = "...";
    }

    public RegClient(String password) {
        this.password = password;
    }
    public boolean getStatus () {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }


}
