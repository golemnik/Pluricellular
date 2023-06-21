package com.golem.loginUser;

import com.golem.userModule.abstractions.AbstractUCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class LoginClientCellFactory extends AbstractUCellFactory {
    public LoginClientCellFactory() {
        super(new Signature(
                "login",
                "login client to list.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("login .+ .+")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        LoginClientCommandCell cell = new LoginClientCommandCell();
//        cell.setClients(getClients());
        return cell.useSignature(signature);
    }
}

