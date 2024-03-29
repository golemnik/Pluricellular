package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.signature.Signature;

import java.util.List;
import java.util.ServiceLoader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class AbstractSystemCellFactory extends AbstractICellCore implements SystemCommandCellFactory {
    private final Signature signature;
    public final Signature getSignature() {
        return signature;
    }

    protected AbstractSystemCellFactory (Signature signature) {
        this.signature = signature;
    }

    @Override
    public abstract AbstractCommand create(List<String> signature);

    @Override
    public String commandDescription() {
        return getSignature().description();
    }

    @Override
    public String creationCommand() {
        return getSignature().command();
    }

    public boolean runAtStart() {
        return false;
    }
    public boolean runAtFinish() {
        return false;
    }

    public boolean signatureCompare(List<String> inputSignature) {
        if (inputSignature.size() != getSignature().patternSignature().size()) return false;
        for (int i = 0; i < inputSignature.size(); i++) {
            if (!Pattern.matches(getSignature().patternSignature().get(i), inputSignature.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<AbstractSystemCellFactory> getSystemCellFactories(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractSystemCellFactory.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
