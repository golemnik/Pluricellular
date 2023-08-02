package com.golem.login;

import com.golem.clientCell.recipient.user.User;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.util.List;
import java.util.Scanner;

public class LoginCommandCell extends AbstractCommand implements SystemCommand {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void activate() {
        Scanner scanner = new Scanner(System.in);
        CellPrinter.setMessage("Input login:");
        String login = scanner.nextLine();
        CellPrinter.setMessage("Input password:");
        String password = scanner.nextLine();
        user.setLogin(login);
        user.setPassword(password);
        setAnswer(List.of("Welcome back, " + login + "!"));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
