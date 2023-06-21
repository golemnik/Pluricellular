package com.golem.netCell.containers;

import java.io.Serializable;

public class UserContainer implements Serializable {
    public final String login;
    public final String password;
    public final DataContainer dataContainer;
    public UserContainer (String login, String password, DataContainer container) {
        this.login = login;
        this.password = password;
        this.dataContainer = container;
    }
}
