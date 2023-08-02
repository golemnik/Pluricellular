package com.golem.login;

import com.golem.clientCell.recipient.userfactories.AbstractUCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class LoginCellFactory extends AbstractUCellFactory implements SystemCommandCellFactory {
    public LoginCellFactory() {
        super(new Signature(
                "login",
                "update login in system.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("login")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }



    @Override
    public AbstractCommand create(List<String> signature) {
        LoginCommandCell command = new LoginCommandCell();
        command.setUser(getUser());
        return command.useSignature(signature);
    }
}
