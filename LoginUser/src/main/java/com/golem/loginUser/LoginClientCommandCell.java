package com.golem.loginUser;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.userModule.Clients;

import java.util.List;

public class LoginClientCommandCell extends AbstractCommand {
    private final String file = "clients.json";
    private Clients clients;
    private String login;
    private String password;

    public LoginClientCommandCell() {
    }
    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public void activate() {

    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("loaded..."));
        login = signature.get(0).split(" ")[1];
        password = signature.get(0).split(" ")[2];
        return this;
    }
}
