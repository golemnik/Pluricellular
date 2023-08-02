package com.golem.clientCell.recipient.user;

import com.golem.netCell.innerMechanics.Crypt;


public class User {
    private String login;
    private String password;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String encrypt (String password) {
        return Crypt.encrypt(password);
    }
}
