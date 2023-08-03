package com.golem.serverCell.clients;

public class RegClient {
    private String password;
    private boolean status = false;

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
